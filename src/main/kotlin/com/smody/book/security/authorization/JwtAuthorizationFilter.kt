package com.smody.book.security.authorization

import com.smody.book.member.domain.MemberRepository
import com.smody.book.security.OAuthPrincipal
import com.smody.book.security.support.extractBearerToken
import com.smody.book.security.jwt.JwtTokenProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthorizationFilter(
    private val memberRepository: MemberRepository,
    private val jwtTokenProvider: JwtTokenProvider
) : OncePerRequestFilter(
) {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        request.extractBearerToken()?.let { token ->
            authorizeIfTokenValid(token)
        }
        chain.doFilter(request, response)
    }

    private fun authorizeIfTokenValid(token: String) {
        if (jwtTokenProvider.isValidToken(token)) {
            authorizeIfMemberExist(token, jwtTokenProvider.getId(token))
        }
    }

    private fun authorizeIfMemberExist(token: String, memberId: Long) {
        if (memberRepository.existsById(memberId)) {
            authorize(OAuthPrincipal(memberId, token))
        }
    }

    private fun authorize(oAuthPrincipal: OAuthPrincipal) {
        val context = SecurityContextHolder.createEmptyContext()
        context.authentication = UsernamePasswordAuthenticationToken(
            oAuthPrincipal,
            null,
            oAuthPrincipal.authorities
        )
        SecurityContextHolder.setContext(context)
    }
}