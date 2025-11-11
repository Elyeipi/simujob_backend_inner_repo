package com.una.backend_simujob.error

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ApiError(
    val localDateTime: String = LocalDateTime.now().toString(),
    val status: HttpStatus,
    val message: String? = null,
    val debugMessage: String? = null,
    var apiSubErrors: MutableList<ApiSubError> = mutableListOf(),
) {
    fun addSubError(apiError: ApiSubError) {
        apiSubErrors.add(apiError)
    }
}
