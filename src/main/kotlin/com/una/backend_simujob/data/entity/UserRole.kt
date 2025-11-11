package com.una.backend_simujob.data.entity

import com.una.backend_simujob.data.embedded.UserRoleId
import jakarta.persistence.*

@Entity
@Table(name = "user_role")
data class UserRole(
    @Id
    var id : UserRoleId,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    var user : User,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    var role : Role
)
