package com.una.backend_simujob.data.repository

import com.una.backend_simujob.data.embedded.UserAchievementId
import com.una.backend_simujob.data.entity.Achievement
import com.una.backend_simujob.data.entity.UserAchievement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface userAchievementRepository : JpaRepository<UserAchievement, UserAchievementId>{

    fun findByUserId(userId: Long): List<UserAchievement>

    @Query("SELECT a FROM Achievement a WHERE a.id NOT IN (" +
            "SELECT ua.achievement.id FROM UserAchievement ua WHERE ua.id.userId = :userId)")
    fun findAchievementsNotRelatedWithUserId(userId: Long): List<Achievement>
}