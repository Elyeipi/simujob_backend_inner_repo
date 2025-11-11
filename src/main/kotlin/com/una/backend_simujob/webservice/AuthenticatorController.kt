package com.una.backend_simujob.webservice

import com.una.backend_simujob.service.dtos.CredentialInput
import com.una.backend_simujob.service.dtos.UserOutput
import com.una.backend_simujob.service.interfaces.AuthenticationService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${url.login}")
class AuthenticatorController(private val authenticationService : AuthenticationService) {
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun login(@RequestBody credentials : CredentialInput) : UserOutput?{
        return authenticationService.login(credentials)
    }
    @GetMapping("/load")
    fun loadAuthenticateUserData(@RequestParam email: String) : UserOutput? {
        return authenticationService.loadAuthenticateUserData(email)
    }
}