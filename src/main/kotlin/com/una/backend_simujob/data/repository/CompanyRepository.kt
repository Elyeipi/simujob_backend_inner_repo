package com.una.backend_simujob.data.repository

import com.una.backend_simujob.data.entity.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : JpaRepository<Company, Long> {
    /**
     * Find a company by its email address.
     *
     * @param email the email address of the company to find
     * @return the company with the specified email, or null if no such company exists
     */
    @Query("SELECT c FROM Company c WHERE c.email = :email")
    fun findByEmail(email: String): Company?
}
