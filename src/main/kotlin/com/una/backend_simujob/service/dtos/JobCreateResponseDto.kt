package com.una.backend_simujob.service.dtos

import java.util.*

/**
 * Returned after successfully creating or fetching a Job.
 */
data class JobCreateResponseDto(
    val id: Long,
    val title: String,
    val shift: String,
    val workMode: String,
    val description: String?,
    val jobUrl: String?,
    val postDate: Date,

    /**
     * A small summary of the posting company.
     * Reuses your existing CompanySummary DTO.
     */
    val company: CompanySummary
)
