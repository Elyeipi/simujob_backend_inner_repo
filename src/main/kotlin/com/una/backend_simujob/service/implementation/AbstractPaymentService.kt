package com.una.backend_simujob.service.implementation

import com.una.backend_simujob.data.repository.UserRepository
import com.una.backend_simujob.data.repository.paymentCardRepository
import com.una.backend_simujob.service.dtos.BuySuscription
import com.una.backend_simujob.service.dtos.PaymentCardDTO
import com.una.backend_simujob.service.dtos.PaymentOutput
import com.una.backend_simujob.service.dtos.UserOutput
import com.una.backend_simujob.service.interfaces.PaymentService
import com.una.backend_simujob.service.mappers.PaymentCardMapper
import com.una.backend_simujob.service.mappers.UserMapper
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
@Transactional
class AbstractPaymentService(
    @Autowired
    private val paymentCardRepository: paymentCardRepository,

    @Autowired
    private val userRepository: UserRepository,

    @Autowired
    private val paymentCardMapper: PaymentCardMapper,

    @Autowired
    private val userMapper: UserMapper
) : PaymentService {

    @Throws(NoSuchElementException::class)
    override fun addPaymentCard(paymentCard: PaymentCardDTO) : PaymentOutput {
        val userEntity = userRepository.findById(paymentCard.userId).orElseThrow { NoSuchElementException(String.format("No user register")) }
        val paymentEntity = paymentCardMapper.paymentCardDTOToPatmentCardEntity(paymentCard)
        paymentEntity.user = userEntity
        return paymentCardMapper.paymentCardEntityToPaymentOutput(paymentCardRepository.save(paymentEntity))
    }

    @Throws(NoSuchElementException::class)
    override fun deletePaymentCard(id: Long) : PaymentOutput{
        val entityPayment = paymentCardRepository.findById(id).get()
        if(entityPayment.id == null){
            throw NoSuchElementException(String.format("No payment card associated with that id"))
        }
        paymentCardRepository.deleteById(id)
        return paymentCardMapper.paymentCardEntityToPaymentOutput(entityPayment)
    }

    @Throws(NoSuchElementException::class)
    override fun getPaymentCards(id: Long) : List<PaymentOutput>?{
        val entityPayments = paymentCardRepository.findAllByUserId(userId = id)
        if(entityPayments.isEmpty()){
            throw NoSuchElementException(String.format("No payments card associated with that user id"))
        }
        return paymentCardMapper.toPaymentOutputList(entityPayments)
    }

    override fun buySuscription(buySuscription: BuySuscription) : UserOutput?{
        val userEntity = userRepository.findById(buySuscription.userId).orElseThrow { NoSuchElementException(String.format("No user register")) }
        userEntity.suscription = buySuscription.newSuscription
        val updateUser = userRepository.save(userEntity)
        updateUser.socialNetwork?.size
        updateUser.education?.size
        return userMapper.userUserEntityToUserOutput(updateUser)
    }

}