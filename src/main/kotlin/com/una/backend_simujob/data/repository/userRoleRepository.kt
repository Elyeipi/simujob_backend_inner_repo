package com.una.backend_simujob.data.repository

import com.una.backend_simujob.data.embedded.UserRoleId
import com.una.backend_simujob.data.entity.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface userRoleRepository : JpaRepository<UserRole, UserRoleId>