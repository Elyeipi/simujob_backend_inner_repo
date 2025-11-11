package com.una.backend_simujob.service.interfaces

import com.una.backend_simujob.service.dtos.BuySuscription
import com.una.backend_simujob.service.dtos.PaymentCardDTO
import com.una.backend_simujob.service.dtos.PaymentOutput
import com.una.backend_simujob.service.dtos.UserOutput

interface PaymentService {

    fun addPaymentCard(paymentCard: PaymentCardDTO) : PaymentOutput //here change String to PaymentCard

    fun deletePaymentCard(id: Long) : PaymentOutput

    fun getPaymentCards(id: Long) : List<PaymentOutput>? //Here change String to PaymentCard

    fun buySuscription(buySuscription: BuySuscription) : UserOutput? //Here change String to PaymentCard
}