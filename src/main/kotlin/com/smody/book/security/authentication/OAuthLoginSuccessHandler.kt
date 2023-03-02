package com.smody.book.security.authentication

import com.fasterxml.jackson.databind.ObjectMapper
import com.smody.book.security.OAuthPrincipal
import com.smody.book.security.dto.TokenResponse
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
        writer.println(objectMapper.writeValueAsString(TokenResponse(oAuthPrincipal)))
        writer.flush()
    }
}