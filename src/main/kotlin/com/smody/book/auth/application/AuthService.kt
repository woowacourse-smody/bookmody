package com.smody.book.auth.application

import com.smody.book.auth.dto.AuthorizationRequest
import com.smody.book.auth.dto.AuthorizationResponse
import com.smody.book.auth.dto.LoginRequest
import com.smody.book.auth.dto.LoginResponse
import com.smody.book.auth.jwt.JwtTokenProvider
import com.smody.book.member.domain.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AuthService(
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

    fun authorize(authorizationRequest: AuthorizationRequest): AuthorizationResponse {
        val token = authorizationRequest.accessToken
        return tokenProvider.extractIdIfValid(token)?.let { id ->
            val isAuthorized = memberRepository.existsById(id)
            AuthorizationResponse(isAuthorized, id)
        } ?: AuthorizationResponse.unauthorized()
    }
}
