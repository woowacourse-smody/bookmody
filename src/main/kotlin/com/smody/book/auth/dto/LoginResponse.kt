package com.smody.book.auth.dto

import com.smody.book.auth.security.OAuthPrincipal

data class LoginResponse(val memberId: Long, val accessToken: String) {
    constructor(oAuthPrincipal: OAuthPrincipal) : this(oAuthPrincipal.memberId, oAuthPrincipal.accessToken)
}
