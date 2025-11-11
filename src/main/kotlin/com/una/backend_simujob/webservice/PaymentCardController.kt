package com.una.backend_simujob.webservice

import com.una.backend_simujob.service.dtos.BuySuscription
import com.una.backend_simujob.service.dtos.PaymentCardDTO
import com.una.backend_simujob.service.dtos.PaymentOutput
import com.una.backend_simujob.service.dtos.UserOutput
import com.una.backend_simujob.service.interfaces.PaymentService
import com.una.backend_simujob.service.interfaces.UserService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${url.payments}")
class PaymentCardController(private val paymentService: PaymentService, private val userService: UserService) {
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun addPaymentCard(@RequestBody paymentCardDTO: PaymentCardDTO) : PaymentOutput{
        return paymentService.addPaymentCard(paymentCardDTO)
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    fun deleteById(@PathVariable id : Long) : PaymentOutput{
        return paymentService.deletePaymentCard(id)
    }

    @GetMapping("{id}")
    fun findPaymentById(@PathVariable id : Long) : List<PaymentOutput>?{
        return paymentService.getPaymentCards(id)
    }

    @PostMapping("/buy", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun buySuscription(@RequestBody buySuscription: BuySuscription) : UserOutput?{
        return paymentService.buySuscription(buySuscription)
    }


}