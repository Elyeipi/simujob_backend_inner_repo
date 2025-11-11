package com.una.backend_simujob.service.dtos

data class CompanyOutPutWithJobs(
    val company: CompanyOutput,
    val jobs: List<JobOutput> = emptyList(),
)
