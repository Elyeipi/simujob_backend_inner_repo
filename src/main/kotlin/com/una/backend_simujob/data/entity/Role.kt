package com.una.backend_simujob.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "role")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long ?= null,

    @Column(name = "name")
    var name : String,

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    var rolePrivileges: MutableSet<RolePrivilege>? = mutableSetOf(),

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var userRole : MutableSet<UserRole>? = mutableSetOf()
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Role

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
