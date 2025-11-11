package com.una.backend_simujob.data.repository

import com.una.backend_simujob.data.entity.Make
import com.una.backend_simujob.data.entity.MakeId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MakeRepository : JpaRepository<Make, MakeId>{
    fun findByMakeUserId(userId: Long): List<Make>
    fun findTop3ByMakeUserIdOrderByIdDesc(userId: Long): List<Make>
}