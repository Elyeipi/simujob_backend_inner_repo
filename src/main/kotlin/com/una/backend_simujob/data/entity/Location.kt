package com.una.backend_simujob.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "locations")
class Location(
    @Id
    @Column(name = "company_id")
    var companyId: Long? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "company_id")
    var company: Company? = null,

    @Column(length = 50)
    var country: String? = null,

    @Column(length = 255)
    var city: String? = null,

    @Column(length = 255)
    var address: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Location

        if (companyId != other.companyId) return false
        if (company != other.company) return false
        if (country != other.country) return false
        if (city != other.city) return false
        if (address != other.address) return false

        return true
    }

    override fun hashCode(): Int {
        var result = companyId?.hashCode() ?: 0
        result = 31 * result + (company?.hashCode() ?: 0)
        result = 31 * result + (country?.hashCode() ?: 0)
        result = 31 * result + (city?.hashCode() ?: 0)
        result = 31 * result + (address?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "${this.country}, ${this.city}, ${this.address}, ${this.company?.legalId}"
    }
}
