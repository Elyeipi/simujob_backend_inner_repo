package com.una.backend_simujob.service.dtos


data class InterviewInput(
    val id: Long? = null,
    val type: String,
    val difficulty: Float,
    val userId : Long
)