package com.una.backend_simujob.data.repository

import com.una.backend_simujob.data.entity.Interview
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InterviewRepository : JpaRepository<Interview, Long> {
    fun readById(id: Long): MutableList<Interview>
}