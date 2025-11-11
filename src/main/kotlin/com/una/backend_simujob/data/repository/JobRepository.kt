package com.una.backend_simujob.data.repository

import com.una.backend_simujob.data.entity.Job
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface JobRepository : JpaRepository<Job, Long>{
    @Query("SELECT j FROM Job j WHERE j.company.id = :companyId")
    fun findByCompanyId(@Param("companyId") companyId: Long): List<Job>

    @Query("SELECT j FROM Job j WHERE j.company.id = :companyId")
    fun findSomeJobsByCompanyId(
        @Param("companyId") companyId: Long,
        pageable: Pageable
    ): List<Job>

    @Query("SELECT j FROM Job j WHERE j.title ILIKE CONCAT('%', :param, '%')")
    fun findByTitleParam(@Param("param") param: String): List<Job>
}
