package com.una.backend_simujob.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long?=null,
    @Column(name = "create_date")
    var createDate: java.util.Date?=null,
    @Column(name = "email", unique = true, nullable = false, length = 255)
    var email: String,
    @Column(name = "enabled", nullable = false)
    var enabled: Boolean?,
    @Column(name="first_name", nullable = false, length = 255)
    var firstName: String,
    @Column(name = "last_name", nullable = false, length = 255)
    var lastName: String,
    @Column(name = "password", nullable = false, length = 255)
    var password: String?,
    @Column(name = "token_expired", nullable = false)
    var tokenExpired: Boolean?,
    @Column(name = "about_user", length = 255)
    var aboutUser: String?,
    @Column(name = "curriculum", unique = true, length = 255)
    var curriculum: String?,
    @Column(name = "suscription", nullable = false)
    var suscription: Int,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var education: MutableList<Education>? = mutableListOf(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var socialNetwork: MutableList<SocialNetwork>? = mutableListOf(),

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var paymentCards : MutableSet<PaymentCard>? = mutableSetOf(),

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var userRole : MutableSet<UserRole>? = mutableSetOf(),

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var userAchievement : MutableSet<UserAchievement>? = mutableSetOf(),
) {

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(other !is User) return false

        if(id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
