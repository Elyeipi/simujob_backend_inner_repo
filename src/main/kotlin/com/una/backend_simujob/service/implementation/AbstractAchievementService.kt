package com.una.backend_simujob.service.implementation

import com.una.backend_simujob.data.embedded.UserAchievementId
import com.una.backend_simujob.data.entity.UserAchievement
import com.una.backend_simujob.data.repository.UserRepository
import com.una.backend_simujob.data.repository.achievementRepository
import com.una.backend_simujob.data.repository.userAchievementRepository
import com.una.backend_simujob.service.dtos.AchievementDTO
import com.una.backend_simujob.service.dtos.UserAchivementDTO
import com.una.backend_simujob.service.interfaces.AchievementService
import com.una.backend_simujob.service.mappers.AchievementMapper
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
@Transactional
class AbstractAchievementService(
    @Autowired
    private val userAchievementRepository: userAchievementRepository,
    @Autowired
    private val userRepository: UserRepository,
    @Autowired
    private val achievementRepository: achievementRepository,
    @Autowired
    private val achievementMapper: AchievementMapper,

) : AchievementService {

    override  fun addAchievement(achievement: AchievementDTO): AchievementDTO{
        val achievementEntity = achievementMapper.achievementDTOToAchievementEntity(achievement)
        val achievementDB = achievementRepository.save(achievementEntity)
        return achievementMapper.achievementEntityToAchievementDTO(achievementDB)
    }

    @Throws(NoSuchElementException::class)
    override fun getAchievementByUser(userId: Long): List<AchievementDTO>? {
        val userAchievements = userAchievementRepository.findByUserId(userId)
        val achievementsEntities = userAchievements.map { achievement -> achievement.achievement}
        if(achievementsEntities.isEmpty()){
            throw NoSuchElementException("No achievement relation with user id: $userId")
        }
        return achievementMapper.entityAchievementListToAchievementDTOList(achievementsEntities)
    }

    @Throws(NoSuchElementException::class)
    override fun associateAchievementWithUser(achievementUser: UserAchivementDTO) : AchievementDTO{
        val userEntity = userRepository.findById(achievementUser.userId).orElseThrow{ NoSuchElementException("No user with id: ${achievementUser.userId}") }
        val achievementEntity = achievementRepository.findById(achievementUser.achievementId).orElseThrow {
            NoSuchElementException("No achievement with id: ${achievementUser.userId}") }
        val userAchievementId = UserAchievementId(userId = userEntity.id!!, achievementId = achievementEntity.id!!)
        val relationDB = userAchievementRepository.findById(userAchievementId).isPresent
        if(relationDB){
            throw Exception("Relation with user and achievement already exist!")
        }
        val relation = UserAchievement(id = userAchievementId, achievement = achievementEntity, user = userEntity)
        userAchievementRepository.save(relation)
        return achievementMapper.achievementEntityToAchievementDTO(achievementEntity)
    }

    @Throws(NoSuchElementException::class)
    override fun getOtherAchievements(userId: Long): List<AchievementDTO>? {
        val achievements = userAchievementRepository.findAchievementsNotRelatedWithUserId(userId)
        if (achievements.isEmpty()){
            throw NoSuchElementException("No achievements available for user with id: $userId, he has all achievements")
        }
        return achievementMapper.entityAchievementListToAchievementDTOList(achievements)
    }

}