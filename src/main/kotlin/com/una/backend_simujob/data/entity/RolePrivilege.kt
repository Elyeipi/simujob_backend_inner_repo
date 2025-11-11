package com.una.backend_simujob.data.entity

import com.una.backend_simujob.data.embedded.RolePrivilegeId
import jakarta.persistence.*

@Entity
@Table(name = "role_privilege")
data class RolePrivilege(
    @Id
    var id : RolePrivilegeId,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("privilegeId")
    @JoinColumn(name = "privilege_id")
    var privilege : Privilege,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    var role : Role
)
