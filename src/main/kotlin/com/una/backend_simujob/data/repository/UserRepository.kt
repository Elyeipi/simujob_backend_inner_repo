package com.una.backend_simujob.data.repository

import com.una.backend_simujob.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>{
    fun findUserByEmail(email: String): User?

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.enabled = true")
    fun findUserByEmailAndEnabled(@Param("email") email: String): User?

    @Modifying
    @Query("UPDATE User u SET u.enabled = :enabled WHERE u.id = :id")
    fun deleteAcoound(@Param("id") id: Long, @Param("enabled") enabled: Boolean)

    @Query("SELECT u FROM User u WHERE u.email = :email")
    fun findByEmail(@Param("email") email: String): User?
}
