package com.smody.book.security.application

import com.smody.book.auth.application.LoginService
import com.smody.book.member.domain.Member
import com.smody.book.member.domain.MemberRepository
import com.smody.book.auth.dto.LoginRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LoginServiceTest {

    @Autowired
    private lateinit var loginService: LoginService

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @DisplayName("로그인을 할 때")
    @Nested
    inner class LoginTest {

        private lateinit var joinedMember: Member

        @BeforeEach
        fun init() {
            joinedMember = memberRepository.save(Member("email@gmail.com", "picture"))
        }

        @DisplayName("이미 가입된 회원은 그대로 반환 한다")
        @Test
        fun joinedLogin() {
            // given
            val loginRequest = LoginRequest("email@gmail.com", "picture")

            // when
            val loginResponse = loginService.login(loginRequest)

            //then
            assertThat(loginResponse.memberId).isEqualTo(joinedMember.id)
        }

        @DisplayName("새로운 회원은 생성 후 반환 한다")
        @Test
        fun joinLogin() {
            // given
            val loginRequest = LoginRequest("email2@gmail.com", "picture")

            // when
            val loginResponse = loginService.login(loginRequest)

            //then
            assertThat(loginResponse.memberId).isNotEqualTo(joinedMember.id)
        }
    }
}
