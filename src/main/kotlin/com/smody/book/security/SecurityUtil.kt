package com.smody.book.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

class SecurityUtil {

    companion object {
        fun authorize(oAuthPrincipal: OAuthPrincipal) {
            val context = SecurityContextHolder.createEmptyContext()
            context.authentication = UsernamePasswordAuthenticationToken(
                oAuthPrincipal,
                null,
                oAuthPrincipal.authorities
            )
            SecurityContextHolder.setContext(context)
        }
    }
}