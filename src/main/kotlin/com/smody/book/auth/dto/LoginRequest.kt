package com.smody.book.auth.dto

import com.smody.book.member.domain.Member

data class LoginRequest(val email: String, val picture: String) {
    fun toMember(): Member = Member(email, picture)
}
