package com.una.backend_simujob.data.repository

import com.una.backend_simujob.data.entity.Education
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface educationRepository : JpaRepository<Education, Long>