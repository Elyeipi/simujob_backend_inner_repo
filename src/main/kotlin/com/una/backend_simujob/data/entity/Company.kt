package com.una.backend_simujob.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "companies")
class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "legal_id", length = 16, nullable = false)
    var legalId: String? = null,

    @Column(length = 255)
    var name: String? = null,

    @Column(length = 255)
    var email: String? = null,

    @Column(name = "password", nullable = true, length = 255)
    var password: String?,

    @Column(name = "contact_phone", length = 20)
    var contactPhone: String? = null,

    @Column(name = "web_site", length = 255)
    var webSite: String? = null,

    @Column(length = 255)
    var industry: String? = null,

    @Column(length = 600)
    var description: String? = null,

    @Column(name = "linkedin_url", length = 255)
    var linkedin: String? = null,

    @Column(name = "facebook_url", length = 255)
    var facebook: String? = null,

    @Column(name = "twitter_url", length = 255)
    var twitter: String? = null,

    @Column(name = "legal_rep_name", length = 255)
    var legalRepName: String? = null,

    @Column(name = "legal_rep_lastnames", length = 255)
    var legalRepLastNames: String? = null,

    @Column(name = "legal_rep_email", length = 255)
    var legalRepEmail: String? = null,

    @OneToOne(mappedBy = "company", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var location: Location? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Company

        if (id != other.id) return false
        if (legalId != other.legalId) return false
        if (name != other.name) return false
        if (email != other.email) return false
        if (contactPhone != other.contactPhone) return false
        if (webSite != other.webSite) return false
        if (industry != other.industry) return false
        if (description != other.description) return false
        if (linkedin != other.linkedin) return false
        if (facebook != other.facebook) return false
        if (twitter != other.twitter) return false
        if (legalRepName != other.legalRepName) return false
        if (legalRepLastNames != other.legalRepLastNames) return false
        if (legalRepEmail != other.legalRepEmail) return false
        if (location != other.location) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (legalId?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (contactPhone?.hashCode() ?: 0)
        result = 31 * result + (webSite?.hashCode() ?: 0)
        result = 31 * result + (industry?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (linkedin?.hashCode() ?: 0)
        result = 31 * result + (facebook?.hashCode() ?: 0)
        result = 31 * result + (twitter?.hashCode() ?: 0)
        result = 31 * result + (legalRepName?.hashCode() ?: 0)
        result = 31 * result + (legalRepLastNames?.hashCode() ?: 0)
        result = 31 * result + (legalRepEmail?.hashCode() ?: 0)
        result = 31 * result + (location?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Company(id=$id, legalId=$legalId, name=$name, email=$email, contactPhone=$contactPhone, webSite=$webSite, industry=$industry, description=$description, linkedin=$linkedin, facebook=$facebook, twitter=$twitter, legalRepName=$legalRepName, legalRepLastNames=$legalRepLastNames, legalRepEmail=$legalRepEmail, location=$location)"
    }
}
