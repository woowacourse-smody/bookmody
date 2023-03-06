package com.smody.book.auth.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

class OAuthPrincipal(val memberId: Long, val accessToken: String) : OAuth2User {

    private val attributes: MutableMap<String, Any> = mutableMapOf()
    override fun getName(): String {
        return "$memberId"
    }

    override fun getAttributes(): MutableMap<String, Any> {
        return attributes
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }
}