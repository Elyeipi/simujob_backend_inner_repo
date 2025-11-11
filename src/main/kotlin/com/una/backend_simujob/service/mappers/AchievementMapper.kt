package com.una.backend_simujob.service.mappers

import com.una.backend_simujob.data.entity.Achievement
import com.una.backend_simujob.service.dtos.AchievementDTO
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface AchievementMapper {
    fun achievementDTOToAchievementEntity(achievement: AchievementDTO): Achievement

    fun achievementEntityToAchievementDTO(achievement: Achievement): AchievementDTO

    fun entityAchievementListToAchievementDTOList(achievements: List<Achievement>) : List<AchievementDTO>
}