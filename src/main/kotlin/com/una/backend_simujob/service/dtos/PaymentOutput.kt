package com.una.backend_simujob.service.dtos

import java.util.*

data class PaymentOutput(
    val id: Long? = null,
    val cardNumber: String,
    val cardHolderName: String,
    val expirationDate: Date,
)
