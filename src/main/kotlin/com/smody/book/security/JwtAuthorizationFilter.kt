package com.smody.book.security

import com.smody.book.member.domain.MemberRepository
import com.smody.book.security.jwt.JwtTokenProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
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
        if (request.existBearerToken()) {
            val accessToken = request.extractBearerToken()
            if (jwtTokenProvider.isValidToken(accessToken)) {
                authenticate(accessToken)
            }
        }
        chain.doFilter(request, response)
    }

    private fun authenticate(accessToken: String) {
        val memberId = jwtTokenProvider.getId(accessToken)
        if (memberRepository.existsById(memberId)) {
            SecurityUtil.authenticate(OAuthPrincipal(memberId, accessToken))
        }
    }

}