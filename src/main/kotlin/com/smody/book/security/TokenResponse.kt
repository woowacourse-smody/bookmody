package com.smody.book.security

data class TokenResponse(val memberId: Long, val accessToken: String) {
    constructor(oAuthPrincipal: OAuthPrincipal) : this(oAuthPrincipal.memberId, oAuthPrincipal.accessToken)
}
