package com.una.backend_simujob.data.repository

import com.una.backend_simujob.data.entity.JobApplication
import com.una.backend_simujob.data.entity.JobApplicationKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JobApplicationRepository : JpaRepository<JobApplication, JobApplicationKey>{
    fun findJobByUserId(userId: Long): List<JobApplication>
}
