package com.una.backend_simujob.service.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.URL

/**
 * Payload to create a new Job for the currently authenticated company.
 */
data class JobCreateDto(
    @field:NotBlank
    val title: String,

    @field:NotBlank
    @JsonProperty("workday")
    val shift: String,

    @field:NotBlank
    @JsonProperty("modality")
    val workMode: String,

    val description: String?,

    @field:URL
    @JsonProperty("link")
    val jobUrl: String?
)

