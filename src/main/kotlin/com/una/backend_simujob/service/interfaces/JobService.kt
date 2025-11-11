package com.una.backend_simujob.service.interfaces

import com.una.backend_simujob.service.dtos.*

interface JobService {

    fun searchJobByParam(params: String): List<JobOutput>? //here change String to Job on return type

    fun getLastJobApplicationsByUser(userId: Long) : List<JobOutput>? //here change String to User and Job

    fun getJobsPostedByCompany(companyId: Long): List<JobOutput>? //Here change String to Job

    fun getCompanyByJobId(jobId: Long): CompanyOutPutWithJobs?

    fun addJob(job: JobInput) : JobOutput //here change String to Job

    fun applyJob(application: ApplicationDTO) : JobOutput
}