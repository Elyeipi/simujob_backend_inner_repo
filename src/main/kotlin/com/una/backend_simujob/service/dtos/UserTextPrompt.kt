package com.una.backend_simujob.service.dtos

data class UserTextPrompt(
    val id: Long,
    val message: String,
    val difficulty: Int,
)