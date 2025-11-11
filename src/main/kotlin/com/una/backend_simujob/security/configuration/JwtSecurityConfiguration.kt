package com.una.backend_simujob.security.configuration

import com.una.backend_simujob.data.repository.CompanyRepository
import com.una.backend_simujob.security.CompanyJwtAuthenticationFilter
import com.una.backend_simujob.security.JwtAuthenticationFilter
import com.una.backend_simujob.security.JwtAuthorizationFilter
import com.una.backend_simujob.service.implementation.AppUserDetailsService
import com.una.backend_simujob.service.implementation.CompanyDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Profile("!initlocal")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class JwtSecurityConfiguration(
    private val userDetailsService: AppUserDetailsService,
    private val companyDetailsService: CompanyDetailsService,
    private val companyRepository: CompanyRepository
) {
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun userAuthProvider() = DaoAuthenticationProvider().apply {
        setUserDetailsService(userDetailsService)
        setPasswordEncoder(passwordEncoder())
    }

    @Bean
    fun companyAuthProvider() = DaoAuthenticationProvider().apply {
        setUserDetailsService(companyDetailsService)
        setPasswordEncoder(passwordEncoder())
    }

    @Bean
    fun authenticationManager(): AuthenticationManager {
        // Order matters: company first, then users
        return ProviderManager(listOf(companyAuthProvider(), userAuthProvider()))
    }

    @Bean
    fun filterChain(
        http: HttpSecurity,
        authManager: AuthenticationManager
    ): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .cors { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests {
                it
                    // public endpoints
                    .requestMatchers(
                        HttpMethod.POST,
                        "/v1/users/login", "/v1/users/signup",
                        "/v1/companies/login", "/v1/companies/signup",
                    ).permitAll()

                    .anyRequest().authenticated()
            }
            // also wire the same providers into Spring’s DSL
            .authenticationProvider(companyAuthProvider())
            .authenticationProvider(userAuthProvider())
            // plug in both login filters
            .addFilter(JwtAuthenticationFilter(authManager))
            .addFilter(CompanyJwtAuthenticationFilter(authManager, companyRepository))
            // then the single authorization filter
            .addFilterAfter(
                JwtAuthorizationFilter(authManager),
                JwtAuthenticationFilter::class.java
            )

        return http.build()
    }
}
