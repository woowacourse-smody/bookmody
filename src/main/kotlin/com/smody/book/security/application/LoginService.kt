package com.smody.book.security.application

import com.smody.book.member.domain.MemberRepository
import com.smody.book.security.dto.LoginRequest
import com.smody.book.security.dto.LoginResponse
import com.smody.book.security.jwt.JwtTokenProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LoginService(
    private val memberRepository: MemberRepository,
    private val tokenProvider: JwtTokenProvider
) {

    @Transactional
    fun login(loginRequest: LoginRequest): LoginResponse {
        val member = memberRepository.findByEmail(loginRequest.email)
            ?: memberRepository.save(loginRequest.toMember())
        val payload = mutableMapOf("id" to member.id!!)
        return LoginResponse(member.id!!, tokenProvider.createToken(payload))
    }
}
