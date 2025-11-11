package com.una.backend_simujob.service.implementation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class HashService(
    @Autowired
    private val passwordEncoder: PasswordEncoder
) {

    fun hashPassword(password: String): String {
        return passwordEncoder.encode(password)
    }

    fun matches(plainPassword: String, hashedPassword: String): Boolean {
        return passwordEncoder.matches(plainPassword, hashedPassword)
    }

}