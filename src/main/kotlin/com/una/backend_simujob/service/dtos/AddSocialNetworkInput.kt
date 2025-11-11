package com.una.backend_simujob.service.dtos

data class AddSocialNetworkInput(
    val newSocialNetwork: SocialNetworkInput
)

data class SocialNetworkInput(
    val socialNetworkName: String,
    val link: String
)
