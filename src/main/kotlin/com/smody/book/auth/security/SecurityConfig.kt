package com.smody.book.auth.security

import com.smody.book.auth.security.authentication.OAuthLoginSuccessHandler
import com.smody.book.auth.security.authentication.PrincipalOAuth2UserService
import com.smody.book.auth.security.authorization.JwtAuthorizationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val principalOAuth2UserService: PrincipalOAuth2UserService,
    private val jwtAuthorizationFilter: JwtAuthorizationFilter,
    private val oAuthLoginSuccessHandler: OAuthLoginSuccessHandler
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        return http
            .csrf {
                it.disable()
            }
            .sessionManagement {
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
                it.userInfoEndpoint().userService(principalOAuth2UserService)
                it.successHandler(oAuthLoginSuccessHandler)
            }

            .addFilterBefore(
                jwtAuthorizationFilter,
                UsernamePasswordAuthenticationFilter::class.java
            )

            .exceptionHandling {
                it.authenticationEntryPoint { _, res, _ -> res.status = HttpStatus.UNAUTHORIZED.value() }
            }

            .build()
    }
}