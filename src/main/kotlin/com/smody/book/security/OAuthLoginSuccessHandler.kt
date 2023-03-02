package com.smody.book.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.nimbusds.common.contenttype.ContentType
import com.smody.book.member.domain.MemberRepository
import com.smody.book.security.jwt.JwtTokenProvider
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class OAuthLoginSuccessHandler(
    private val memberRepository: MemberRepository,
    private val jwtTokenProvider: JwtTokenProvider,
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