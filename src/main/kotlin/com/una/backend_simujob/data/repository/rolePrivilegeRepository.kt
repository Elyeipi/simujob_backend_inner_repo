package com.una.backend_simujob.data.repository

import com.una.backend_simujob.data.embedded.RolePrivilegeId
import com.una.backend_simujob.data.entity.RolePrivilege
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface rolePrivilegeRepository : JpaRepository<RolePrivilege, RolePrivilegeId>