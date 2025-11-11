package com.una.backend_simujob.data.repository

import com.una.backend_simujob.data.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface roleRepository : JpaRepository<Role, Long>{
    fun findByName(@Param("name") name : String) : Optional<Role>
}