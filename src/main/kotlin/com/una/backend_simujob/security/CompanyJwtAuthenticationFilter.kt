package com.una.backend_simujob.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.una.backend_simujob.data.repository.CompanyRepository
import com.una.backend_simujob.security.shared.key
import com.una.backend_simujob.service.dtos.CompanyAuthResponse
import com.una.backend_simujob.service.dtos.CompanySummary
import com.una.backend_simujob.service.dtos.CredentialInput
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*

class CompanyJwtAuthenticationFilter(
    authManager: AuthenticationManager,
    private val companyRepository: CompanyRepository
) : UsernamePasswordAuthenticationFilter() {

    private val objectMapper = ObjectMapper()

    init {
        authenticationManager = authManager
        setFilterProcessesUrl("/v1/companies/login")
    }

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Authentication {
        if (request.method != "POST") {
            throw AuthenticationServiceException("Unsupported authentication method: ${request.method}")
        }
        val creds = objectMapper
            .readValue(request.inputStream, CredentialInput::class.java)

        val authToken = UsernamePasswordAuthenticationToken(
            creds.email,
            creds.password,
            emptyList()
        )
        return authenticationManager.authenticate(authToken)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val principal = authResult.principal as org.springframework.security.core.userdetails.User

        // build JWT
        val rawToken = Jwts.builder()
            .signWith(key(), SignatureAlgorithm.HS512)
            .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
            .setIssuer(SecurityConstants.TOKEN_ISSUER)
            .setAudience(SecurityConstants.TOKEN_AUDIENCE)
            .setSubject(principal.username)
            .setExpiration(Date(System.currentTimeMillis() + SecurityConstants.TOKEN_LIFETIME))
            .compact()

        // fetch company info
        val company = companyRepository.findByEmail(principal.username)
            ?: throw IllegalStateException("Authenticated company not found")

        val summary = CompanySummary(
            id = company.id!!,
            name = company.name.orEmpty(),
            email = company.email.orEmpty()
        )

        // prepare response DTO
        val authResponse = CompanyAuthResponse(
            token = rawToken,
            expiresIn = SecurityConstants.TOKEN_LIFETIME,
            company = summary
        )

        // send header + body
        response.addHeader(
            HttpHeaders.AUTHORIZATION,
            "${SecurityConstants.TOKEN_PREFIX}$rawToken"
        )
        response.contentType = SecurityConstants.APPLICATION_JSON
        response.characterEncoding = SecurityConstants.UTF_8
        response.writer.apply {
            print(objectMapper.writeValueAsString(authResponse))
            flush()
        }
    }
}
