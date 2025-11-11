package com.una.backend_simujob.data.entity

import jakarta.persistence.*
import java.io.Serializable
import java.util.Date


@Entity
@Table(name = "job_application")
class JobApplication(
    @EmbeddedId
    var id: JobApplicationKey = JobApplicationKey(),

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null,

    @MapsId("jobId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    var job: Job? = null,

    @Column(name = "application_date", nullable = false)
    var applicationDate: Date? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JobApplication

        if (id != other.id) return false
        if (user != other.user) return false
        if (job != other.job) return false
        if (applicationDate != other.applicationDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (user?.hashCode() ?: 0)
        result = 31 * result + (job?.hashCode() ?: 0)
        result = 31 * result + applicationDate.hashCode()
        return result
    }

    override fun toString(): String {
        return "${this.user?.firstName} ${this.job?.title}, ${this.applicationDate}"
    }
}

// composite PK for job_application
@Embeddable
data class JobApplicationKey(
    @Column(name = "user_id")
    var userId: Long = 0,

    @Column(name = "job_id")
    var jobId: Long = 0
) : Serializable
