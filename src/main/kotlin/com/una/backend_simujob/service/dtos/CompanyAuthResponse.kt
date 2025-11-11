package com.una.backend_simujob.service.dtos

import com.una.backend_simujob.security.SecurityConstants

data class CompanyAuthResponse(
    val token: String,
    val tokenType: String = SecurityConstants.TOKEN_PREFIX.trim(),  // “Bearer”
    val expiresIn: Long,
    val company: CompanySummary
)

data class CompanySummary(
    val id: Long,
    val name: String,
    val email: String
)
