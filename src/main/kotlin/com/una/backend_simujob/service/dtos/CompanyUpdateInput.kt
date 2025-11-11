package com.una.backend_simujob.service.dtos

data class CompanyUpdateInput(
    val juridicalId: String? = null,
    val companyEmail: String? = null,
    val password: String? = null,
    val countryCity: String? = null,
    val contactPhone: String? = null,
    val businessWebsite: String? = null,
    val industrySector: String? = null,
    val shortDescription: String? = null,
    val socialMedia: CompanySocialMedia? = null,
    val legalRepresentative: CompanyLegalRepresentative? = null
)



