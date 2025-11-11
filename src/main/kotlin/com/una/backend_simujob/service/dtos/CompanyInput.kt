package com.una.backend_simujob.service.dtos

data class CompanyInput(
    val id: Long? = null,
    val legalId: String,
    val name: String,
    val email: String,
    val webSite: String? = null,
    val industry: String,
    val description: String,
    val location: LocationDTO,
)
