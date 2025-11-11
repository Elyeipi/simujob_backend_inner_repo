package com.una.backend_simujob.service.dtos

data class CVAIRequest(
    val model: String = "gpt-3.5-turbo",
    val cvText: String = ""
)
