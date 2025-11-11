package com.una.backend_simujob.data.entity

import jakarta.persistence.*

@Entity
@Table (name = "paymentCard")
data class PaymentCard (
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    var id : Long ?= null,
    @Column(name = "card_holder", nullable = false)
    var cardHolder : String,
    @Column(name = "card_number", nullable = false)
    var cardNumber : String,
    @Column(name = "code", nullable = false)
    var code : String,
    @Column(name = "expiration_date", nullable = false)
    var expirationDate : java.util.Date,
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null,
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PaymentCard

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "${this.cardHolder}, ${this.cardNumber}, ${this.code}, ${this.expirationDate}, ${this.user?.firstName}}"
    }
}