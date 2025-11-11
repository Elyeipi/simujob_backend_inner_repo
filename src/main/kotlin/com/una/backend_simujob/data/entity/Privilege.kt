package com.una.backend_simujob.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "privilege")
data class Privilege(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,
    @Column(name = "name")
    var name : String,

    @OneToMany(mappedBy = "privilege", fetch = FetchType.LAZY)
    var rolePrivileges: MutableSet<RolePrivilege>? = mutableSetOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Privilege

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
