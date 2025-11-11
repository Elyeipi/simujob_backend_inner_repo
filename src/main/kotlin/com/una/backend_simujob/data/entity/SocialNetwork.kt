package com.una.backend_simujob.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "social_network")
class SocialNetwork(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "social_network_name", length = 50, nullable = false)
    var socialNetworkName: String? = null,

    @Column(length = 255)
    var link: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SocialNetwork

        if (id != other.id) return false
        if (socialNetworkName != other.socialNetworkName) return false
        if (link != other.link) return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (socialNetworkName?.hashCode() ?: 0)
        result = 31 * result + (link?.hashCode() ?: 0)
        result = 31 * result + (user?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "${this.socialNetworkName}, ${this.link}, ${this.user?.firstName}"
    }
}
