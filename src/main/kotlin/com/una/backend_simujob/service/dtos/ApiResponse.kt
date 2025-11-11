package com.una.backend_simujob.service.dtos

data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null
)

