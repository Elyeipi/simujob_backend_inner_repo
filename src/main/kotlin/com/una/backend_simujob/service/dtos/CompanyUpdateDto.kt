package com.una.backend_simujob.service.dtos

data class CompanyUpdateDto(
    val company: CompanyInfo? = null,
    val contact: ContactInfo? = null,
    val address: AddressInfo? = null,
    val legalRep: LegalRepInfo? = null,
    val business: BusinessInfo? = null,
    val social: SocialInfo? = null
)

data class CompanyInfo(val name: String? = null, val legalId: String? = null)
data class ContactInfo(val phone: String? = null, val website: String? = null)
data class AddressInfo(val country: String? = null, val city: String? = null, val address: String? = null)
data class LegalRepInfo(val firstName: String? = null, val lastName: String? = null, val email: String? = null)
data class BusinessInfo(val sector: String? = null, val description: String? = null)
data class SocialInfo(val linkedin: String? = null, val facebook: String? = null, val twitter: String? = null)
