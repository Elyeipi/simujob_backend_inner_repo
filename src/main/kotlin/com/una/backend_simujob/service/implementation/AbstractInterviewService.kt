package com.una.backend_simujob.service.implementation

import com.una.backend_simujob.data.embedded.UserAchievementId
import com.una.backend_simujob.data.entity.*
import com.una.backend_simujob.data.repository.*
import com.una.backend_simujob.service.dtos.*
import com.una.backend_simujob.service.interfaces.InterviewService
import com.una.backend_simujob.service.mappers.InterviewMapper
import com.una.backend_simujob.service.mappers.InterviewResultMapper
import com.una.backend_simujob.service.mappers.MakeMapper
import jakarta.transaction.Transactional
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Date
import kotlin.jvm.Throws

@Service
@Transactional
class AbstractInterviewService(
    @Autowired
    private val interviewRepository: InterviewRepository,
    @Autowired
    private val interviewResultRepository: InterviewResultRepository,
    @Autowired
    private val userRepository: UserRepository,

    @Autowired
    private val userAchievementRepository: userAchievementRepository,

    @Autowired
    private val achievementRepository: achievementRepository,

    @Autowired
    private val makeRepository: MakeRepository,
    @Autowired
    private val interviewMapper: InterviewMapper,
    @Autowired
    private val makeMapper: MakeMapper,
    @Autowired
    private val interviewResultMapper: InterviewResultMapper,
    @Autowired
    private val aiService: OpenAIService
) : InterviewService {

    @Throws(NoSuchElementException::class)
    override fun getLastInterviews(userId: Long) : List<InterviewOutput>?{
        val relations = makeRepository.findTop3ByMakeUserIdOrderByIdDesc(userId)
        if(relations.isEmpty()){
            throw NoSuchElementException("No interviews found for user with id: $userId")
        }
        return makeMapper.makeListToInterviewOutputList(relations)
    }

    override fun getResultsByInterviewId(interviewId: Long): InterviewResultOutPut?{
        val interview = interviewRepository.findById(interviewId).orElseThrow { NoSuchElementException("No interview found with id: $interviewId") }
        val interviewResult = interview.interviewResult ?: throw NoSuchElementException("No interview result found with id: $interviewId")
        return interviewResultMapper.entityResultToInterviewResultOutPut(interviewResult)
    }

    @Throws(NoSuchElementException::class)
    override fun createInterview(interview: InterviewInput): InterviewOutput {
        val entityInterview = interviewMapper.interviewInputToInterviewEntity(interview)
        val userDB = userRepository.findById(interview.userId).orElseThrow { NoSuchElementException(String.format("No user associated with that user id")) }
        val savedInterview = interviewRepository.save(entityInterview)
        val makeId = MakeId(userId = userDB.id, interviewId = savedInterview.id)
        val make = Make(id = makeId, makeUser = userDB, makeInterview = savedInterview, interviewDate = Date())
        makeRepository.save(make)
        return interviewMapper.interviewEntityToInterviewOutPut(savedInterview)
    }

    @Throws(NoSuchElementException::class)
    override fun finishInterview(feedbackInput: FeedbackInput) : InterviewResultOutPut{
        val feedback = runBlocking { aiService.feedback(feedbackInput) }
        val interviewResultDTO = InterviewResultOutPut(score = feedback.score, feedback = feedback.feedback)
        val interviewResultEntity = interviewResultMapper.resultOutPutToInterviewResult(interviewResultDTO)
        val interview = interviewRepository.findById(feedbackInput.interviewId).orElseThrow {
            NoSuchElementException("Interview with ID ${feedbackInput.interviewId} not found")
        }
        interviewResultEntity.interview = interview

        val interviewOutPut = interviewResultRepository.save(interviewResultEntity)

        /* ACHIEVEMENT RECOGNIZER CODE FRAGMENT*/
        var listAchievements : List<Achievement> = userAchievementRepository.findAchievementsNotRelatedWithUserId(feedbackInput.id)
        var listInterviews : Int = makeRepository.findByMakeUserId(feedbackInput.id).count();

        print("INTERVIEWS: ${listInterviews}")

        var userEntity = userRepository.findById(feedbackInput.id).get()
        listAchievements.map {
            if (it.interviewsRequested <= listInterviews){
                var relationAchievementU = UserAchievementId(userId = feedbackInput.id, achievementId = it.id!!)
                val relationDB = userAchievementRepository.findById(relationAchievementU).isPresent
                if(!relationDB){
                    val relation = UserAchievement(id = relationAchievementU, achievement = it, user = userEntity)
                    userAchievementRepository.save(relation)
                }
            }
        }
        /* ACHIEVEMENT RECOGNIZER CODE FRAGMENT*/

        return interviewResultMapper.entityResultToInterviewResultOutPut(interviewOutPut)
    }

}