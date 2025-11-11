package com.una.backend_simujob.service.dtos

data class UpdateAboutMeUser(
    val id: Long? = null,
    val enabled: Boolean,
    val tokenExpired: Boolean,
    val aboutUser: String? = null,
    )
