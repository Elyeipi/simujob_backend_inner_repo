package com.una.backend_simujob.service.interfaces

import com.una.backend_simujob.service.dtos.AchievementDTO
import com.una.backend_simujob.service.dtos.UserAchivementDTO


interface AchievementService {

    fun addAchievement(achievement: AchievementDTO): AchievementDTO

    fun getAchievementByUser(userId: Long): List<AchievementDTO>? //here change String to Achievement on return and change String to UserDTO on param

    fun associateAchievementWithUser(achievementUser: UserAchivementDTO) : AchievementDTO

    fun getOtherAchievements(userId: Long): List<AchievementDTO>? //here change String to Achievement
}