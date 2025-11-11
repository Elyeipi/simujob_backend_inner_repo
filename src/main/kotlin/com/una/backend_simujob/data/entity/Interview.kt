package com.una.backend_simujob.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "interview")
data class Interview(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "type", nullable = false, length = 5)
    var type: String,
    @Column(name = "difficulty", nullable = false)
    var difficulty: Float,

    @OneToOne(mappedBy = "interview", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var interviewResult: InterviewResult? = null,

    @OneToMany(mappedBy = "makeInterview", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var users: MutableList<Make>? = mutableListOf(),
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Interview

        if (id != other.id) return false
        if (difficulty != other.difficulty) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "${this.type}, ${this.difficulty}"
    }
}
