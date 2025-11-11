package com.una.backend_simujob.service.dtos

data class ChatResponse(
    val id: String?,
    val created: Long?,
    val model: String?,
    val choices: List<Choice>?
)