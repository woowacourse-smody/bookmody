package com.smody.book.auth.security.authentication

import com.fasterxml.jackson.databind.ObjectMapper
import com.smody.book.auth.security.OAuthPrincipal
import com.smody.book.auth.dto.LoginResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class OAuthLoginSuccessHandler(
    private val objectMapper: ObjectMapper
) : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val oAuthPrincipal = authentication.principal as OAuthPrincipal
        response.contentType = "application/json;charset=UTF-8"
        val writer = response.writer
        writer.println(objectMapper.writeValueAsString(LoginResponse(oAuthPrincipal)))
        writer.flush()
    }
}