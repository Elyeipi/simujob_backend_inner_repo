package com.una.backend_simujob.data.embedded

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class UserAchievementId(
    var userId : Long = 0,
    var achievementId : Long = 0
) : Serializable
