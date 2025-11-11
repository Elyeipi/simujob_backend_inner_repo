package com.una.backend_simujob.service.dtos


data class UserInput(
    val id: Long? = null,
    var createDate: java.util.Date? = null,
    val email: String,
    val enabled: Boolean,
    val password: String,
    val firstName: String,
    val lastName: String,
    val tokenExpired: Boolean,
    val aboutUser: String? = null,
    val curriculum: String? = null,
    val suscription: Int,

    val education: MutableList<EducationDTO>? = mutableListOf(),
    val socialNetwork: MutableList<SocialNetworkDTO>? = mutableListOf()
)