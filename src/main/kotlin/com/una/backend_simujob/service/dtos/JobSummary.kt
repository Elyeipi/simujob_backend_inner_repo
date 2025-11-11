package com.una.backend_simujob.service.dtos

/**
 * Data class representing a summary of a job offer. (show in the list of job offers)
 */
data class JobSummary(
    val id: Long,
    val title: String,
    val postDate: String
)
