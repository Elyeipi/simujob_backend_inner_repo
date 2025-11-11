package com.una.backend_simujob.service.mappers

import com.una.backend_simujob.data.entity.PaymentCard
import com.una.backend_simujob.service.dtos.PaymentCardDTO
import com.una.backend_simujob.service.dtos.PaymentOutput
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring",
    uses = [UserMapper::class],
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface PaymentCardMapper {
    @Mapping(source = "cardHolder", target = "cardHolderName")
    @Mapping(source = "cardNumber", target = "cardNumber")
    @Mapping(source = "expirationDate", target = "expirationDate")
    @Mapping(source = "code",            target = "cvv")
    fun paymentCardEntityToPaymentCardDto(entity: PaymentCard): PaymentCardDTO

    @Mapping(source = "cardHolderName", target = "cardHolder")
    @Mapping(source = "cvv",            target = "code")
    fun paymentCardDTOToPatmentCardEntity(dto: PaymentCardDTO): PaymentCard

    @Mapping(source = "cardHolder", target = "cardHolderName")
    @Mapping(source = "cardNumber", target = "cardNumber")
    @Mapping(source = "expirationDate", target = "expirationDate")
    fun paymentCardEntityToPaymentOutput(paymentCard: PaymentCard): PaymentOutput

    fun toPaymentCardDtoList(entities: List<PaymentCard>): List<PaymentCardDTO>

    fun toPaymentOutputList(entities: List<PaymentCard>): List<PaymentOutput>
}
