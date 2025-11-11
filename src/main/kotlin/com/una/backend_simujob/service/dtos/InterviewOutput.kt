package com.una.backend_simujob.service.dtos

import java.util.*

data class InterviewOutput(
    val id: Long? = null,
    val date: Date,
    val type: String,
    val difficulty: Float,
    val interviewResult: InterviewResultOutPut? = null,
)
