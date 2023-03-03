package com.smody.book.security.authentication

import com.smody.book.security.OAuthPrincipal
import com.smody.book.security.application.LoginService
import com.smody.book.security.dto.LoginRequest
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PrincipalOAuth2UserService(private val loginService: LoginService) : DefaultOAuth2UserService() {

    @Transactional
    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        val oAuth2User = super.loadUser(validateUserRequestNull(userRequest))
        val loginResponse = loginService.login(oAuth2User.toLoginRequest())
        return OAuthPrincipal(loginResponse.memberId, loginResponse.accessToken)
    }

    private fun validateUserRequestNull(userRequest: OAuth2UserRequest?) =
        userRequest ?: throw IllegalStateException("구글에서 사용자 정보를 받아오지 못했습니다.")

    private fun OAuth2User.toLoginRequest(): LoginRequest =
        LoginRequest(getAttribute("email")!!, getAttribute("picture")!!)
}
