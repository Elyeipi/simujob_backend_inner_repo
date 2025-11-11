package com.una.backend_simujob.service.dtos

data class AchievementDTO(
    val id: Long? = null,
    val title: String,
    val description: String,
    val interviewsRequested: Int,
)
