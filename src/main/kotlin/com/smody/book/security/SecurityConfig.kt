package com.smody.book.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        return http
            .csrf {
                it.disable()
            }
            .sessionManagement{
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .formLogin {
                it.disable()
            }
            .httpBasic {
                it.disable()
            }

            .authorizeHttpRequests {
                it.requestMatchers("/auth/**").authenticated()
                it.anyRequest().permitAll()
            }

            .oauth2Login {
                it.defaultSuccessUrl("/auth/login")
            }

            .build()
    }
}