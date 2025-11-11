package com.una.backend_simujob.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "education")
data class Education(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "institution_name")
    var institutionName: String,
    @Column(name = "degree")
    var degree: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Education

        if (id != other.id) return false
        if (institutionName != other.institutionName) return false
        if (degree != other.degree) return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "${this.institutionName}, ${this.degree}, ${this.user?.firstName}"
    }
}
