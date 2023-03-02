package com.smody.book.security

import com.smody.book.member.domain.Member
import com.smody.book.member.domain.MemberRepository
import com.smody.book.security.jwt.JwtTokenProvider
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PrincipalOAuth2UserService(
    private val memberRepository: MemberRepository,
    private val jwtTokenProvider: JwtTokenProvider
): DefaultOAuth2UserService() {

    @Transactional
    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        validateRequestNull(userRequest)
        val member = loadMember(super.loadUser(userRequest))
        val payload = mutableMapOf<String, Any>()
        payload["id"] = member.id!!
        return OAuthPrincipal(member.id!!, jwtTokenProvider.createToken(payload))
    }

    private fun validateRequestNull(userRequest: OAuth2UserRequest?) {
        if (userRequest == null) {
            throw IllegalStateException("구글에서 사용자 정보를 받아오지 못했습니다.")
        }
    }

    private fun loadMember(oAuth2User: OAuth2User): Member {
        val email = oAuth2User.getAttribute<String>("email")!!
        return memberRepository.findByEmail(email) ?: join(oAuth2User, email)
    }

    private fun join(oAuth2User: OAuth2User, email: String): Member {
        val profile = oAuth2User.getAttribute<String>("picture")!!
        return memberRepository.save(Member(email, profile))
    }
}