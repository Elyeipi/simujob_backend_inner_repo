package com.una.backend_simujob.data.entity

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "job")
class Job(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(length = 50, nullable = false)
    var title: String? = null,

    @Column(length = 255)
    var shift: String? = null,

    @Column(name = "work_mode", length = 10)
    var workMode: String? = null,

    @Column(length = 500)
    var description: String? = null,

    @Column(name = "job_url", length = 255)
    var jobUrl: String? = null,

    @Column(name = "post_date", nullable = false)
    var postDate: Date? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    var company: Company? = null
) {
    @OneToMany(
        mappedBy = "job",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    var applications: MutableList<JobApplication> = mutableListOf()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Job

        if (id != other.id) return false
        if (title != other.title) return false
        if (shift != other.shift) return false
        if (workMode != other.workMode) return false
        if (description != other.description) return false
        if (jobUrl != other.jobUrl) return false
        if (postDate != other.postDate) return false
        if (company != other.company) return false
        if (applications != other.applications) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (shift?.hashCode() ?: 0)
        result = 31 * result + (workMode?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (jobUrl?.hashCode() ?: 0)
        result = 31 * result + postDate.hashCode()
        result = 31 * result + (company?.hashCode() ?: 0)
        result = 31 * result + applications.hashCode()
        return result
    }

    override fun toString(): String {
        return "${this.title}, ${this.shift}, ${this.postDate}, ${this.workMode}, ${this.description}, ${this.company?.name}"
    }
}
