package com.una.backend_simujob.service.dtos

data class UserOutput(
    val id: Long? = null,
    val email: String,
    val enabled: Boolean,
    val firstName: String,
    val lastName: String,
    val tokenExpired: Boolean,
    val aboutUser: String? = null,
    val curriculum: String? = null,
    val suscription: Int,

    val education: MutableList<EducationDTO>? = mutableListOf(),
    val socialNetwork: MutableList<SocialNetworkDTO>? = mutableListOf(),
)
