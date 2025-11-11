package com.una.backend_simujob.service.interfaces

import com.una.backend_simujob.service.dtos.CredentialInput
import com.una.backend_simujob.service.dtos.UserOutput

interface AuthenticationService {

    fun login(credentials: CredentialInput) : UserOutput? //Here change String param to credentialDTO and change String retruns to LoginResult

    fun loadAuthenticateUserData(email: String): UserOutput?

    fun logOut(): String //Here change String
}