package com.una.backend_simujob.data.repository

import com.una.backend_simujob.data.entity.PaymentCard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface paymentCardRepository : JpaRepository<PaymentCard, Long>{
    fun findAllByUserId(userId: Long): List<PaymentCard>
}