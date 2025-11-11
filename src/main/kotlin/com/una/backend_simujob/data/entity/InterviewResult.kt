package com.una.backend_simujob.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "interview_result")
data class InterviewResult(
    @Id
    var id: Long? = null,
    @Column(name = "score", nullable = false)
    var score: Float,
    @Column(name = "feedback", nullable = false, length = 255)
    var feedback: String,

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "interview_id", nullable = false)
    var interview: Interview? = null,
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as InterviewResult

        if (id != other.id) return false
        if (score != other.score) return false
        if (feedback != other.feedback) return false
        if (interview != other.interview) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
