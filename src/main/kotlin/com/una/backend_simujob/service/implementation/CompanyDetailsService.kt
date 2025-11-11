package com.una.backend_simujob.service.implementation

import com.una.backend_simujob.data.repository.CompanyRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CompanyDetailsService(
    private val companyRepository: CompanyRepository
) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val company = companyRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("No company with email $email")
        return User.builder()
            .username(company.email!!)
            .password(company.password!!)
            .authorities(listOf(SimpleGrantedAuthority("COMPANY")))
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build()
    }
}
