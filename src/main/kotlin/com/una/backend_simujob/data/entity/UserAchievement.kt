package com.una.backend_simujob.data.entity

import com.una.backend_simujob.data.embedded.UserAchievementId
import jakarta.persistence.*

@Entity
@Table(name = "user_achievement")
data class UserAchievement(
    @Id
    var id : UserAchievementId,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("achievementId")
    @JoinColumn(name = "achievement_id")
    var achievement: Achievement
)
