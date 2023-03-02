package com.smody.book.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

class SecurityUtil {

    companion object {
        fun authenticate(principalDetails: OAuthPrincipal) {
            val context = SecurityContextHolder.createEmptyContext()
            context.authentication = UsernamePasswordAuthenticationToken(
                principalDetails,
                null,
                principalDetails.authorities
            )
            SecurityContextHolder.setContext(context)
        }
    }
}