package com.smody.book.auth.security.authorization

import com.smody.book.auth.application.AuthService
import com.smody.book.auth.dto.AuthorizationRequest
import com.smody.book.auth.security.OAuthPrincipal
import com.smody.book.auth.support.extractBearerToken
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthorizationFilter(private val authService: AuthService) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        request.extractBearerToken()?.let { token ->
            val authorization = authService.authorize(AuthorizationRequest(token))
            if (authorization.isAuthorized) {
                configurerSecurityContext(OAuthPrincipal(authorization.memberId!!, token))
            }
        }
        chain.doFilter(request, response)
    }

    private fun configurerSecurityContext(oAuthPrincipal: OAuthPrincipal) {
        val context = SecurityContextHolder.createEmptyContext()
        context.authentication = UsernamePasswordAuthenticationToken(
            oAuthPrincipal,
            null,
            oAuthPrincipal.authorities
        )
        SecurityContextHolder.setContext(context)
    }
}