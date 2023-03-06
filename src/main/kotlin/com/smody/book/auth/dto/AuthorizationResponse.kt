package com.smody.book.auth.dto

data class AuthorizationResponse(val isAuthorized: Boolean, val memberId: Long?) {

    companion object {
        fun unauthorized(): AuthorizationResponse = AuthorizationResponse(false, null)
    }
}