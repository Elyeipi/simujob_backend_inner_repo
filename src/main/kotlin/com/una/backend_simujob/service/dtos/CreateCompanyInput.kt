package com.una.backend_simujob.service.dtos

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.URL

/**
 * Data‑transfer object used by the onboarding API that registers a new company.
 * The fields mirror the state slices used in the React `RegisterCompany` component
 * so the JSON can be sent directly without additional reshaping.
 */
@Suppress("unused")
data class CompanyProfile(
    val company: CompanyFields,
    val contact: ContactFields,
    val address: AddressFields,
    val legalRep: LegalRepFields,
    val business: BusinessFields,
    val social: SocialFields,
) {

}

/** Basic company credentials */
data class CompanyFields(
    @field:NotBlank
    val name: String,

    @field:NotBlank
    val legalId: String,

    @field:Email
    val email: String,

    /**
     * Raw password (will be hashed in the service layer).
     * We only validate the length here.
     */
    @field:Size(min = 8, max = 255)
    val password: String,
)

/** Optional public contact information */
data class ContactFields(
    val phone: String?,
    @field:URL
    val website: String?,
)

/** Physical address of the headquarters */
data class AddressFields(
    val country: String?,
    val city: String?,
    val address: String?,
)

/** Legal representative (signatory) */
data class LegalRepFields(
    @field:NotBlank
    val firstName: String,
    @field:NotBlank
    val lastName: String,
    @field:Email
    val email: String,
)

/** Industrial classification & elevator pitch */
data class BusinessFields(
    @field:NotBlank
    val sector: String,
    val description: String?,
)

/** Public social‑media profiles */
data class SocialFields(
    @field:URL
    val linkedin: String?,
    @field:URL
    val facebook: String?,
    @field:URL
    val twitter: String?,
)
