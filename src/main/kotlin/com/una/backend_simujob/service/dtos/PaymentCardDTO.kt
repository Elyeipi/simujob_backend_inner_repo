package com.una.backend_simujob.service.dtos

import java.util.Date

data class PaymentCardDTO(
    val id: Long? = null,
    val cardNumber: String,
    val cardHolderName: String,
    val expirationDate: Date,
    val cvv: String,
    val userId: Long
)
