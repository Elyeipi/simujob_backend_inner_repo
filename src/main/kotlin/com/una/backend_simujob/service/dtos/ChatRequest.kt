package com.una.backend_simujob.service.dtos

data class ChatRequest(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>
)
