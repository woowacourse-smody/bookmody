package com.smody.book.security.dto

import com.smody.book.security.OAuthPrincipal

data class LoginResponse(val memberId: Long, val accessToken: String) {
    constructor(oAuthPrincipal: OAuthPrincipal) : this(oAuthPrincipal.memberId, oAuthPrincipal.accessToken)
}
