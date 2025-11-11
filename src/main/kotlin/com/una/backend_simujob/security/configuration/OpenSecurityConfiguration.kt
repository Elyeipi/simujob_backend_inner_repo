package com.una.backend_simujob.security.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain


@Profile("initlocal")
@Configuration
@EnableWebSecurity
class OpenSecurityConfiguration {
        @Bean
        fun filterChain(http : HttpSecurity) : SecurityFilterChain{
            http
                .csrf{
                    it.disable()
                }
                .cors{
                    it.disable()
                }
                .authorizeHttpRequests{
                    it
                        .anyRequest().authenticated()
                }

            return http.build()
        }
}