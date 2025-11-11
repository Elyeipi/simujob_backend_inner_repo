package com.una.backend_simujob.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "achievement")
data class Achievement(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,

    @Column(name = "title")
    var title : String,

    @Column(name = "description")
    var description : String,

    @Column(name = "interviewsRequested")
    var interviewsRequested : Int,

    @OneToMany(mappedBy = "achievement", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var userAchievement : MutableSet<UserAchievement>? = mutableSetOf()
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Achievement

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "${this.title}, ${this.description}"
    }
}
