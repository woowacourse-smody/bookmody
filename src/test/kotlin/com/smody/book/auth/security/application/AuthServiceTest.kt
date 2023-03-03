package com.smody.book.auth.security.application

import com.smody.book.DatabaseCleaner
import com.smody.book.auth.application.AuthService
import com.smody.book.auth.dto.AuthorizationRequest
import com.smody.book.auth.dto.LoginRequest
import com.smody.book.auth.jwt.JwtTokenProvider
import com.smody.book.member.domain.Member
import com.smody.book.member.domain.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private lateinit var authService: AuthService

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var tokenProvider: JwtTokenProvider

    @Autowired
    private lateinit var databaseCleaner: DatabaseCleaner

    private lateinit var joinedMember: Member
    @BeforeEach
    fun init() {
        joinedMember = memberRepository.save(Member("email@gmail.com", "picture"))
    }

    @AfterEach
    fun clear() {
        databaseCleaner.truncateTables()
    }

    @DisplayName("로그인을 할 때")
    @Nested
    inner class LoginTest {

        @DisplayName("이미 가입된 회원은 그대로 반환 한다")
        @Test
        fun joinedLogin() {
            // given
            val loginRequest = LoginRequest("email@gmail.com", "picture")

            // when
            val loginResponse = authService.login(loginRequest)

            //then
            assertThat(loginResponse.memberId).isEqualTo(joinedMember.id)
        }

        @DisplayName("새로운 회원은 생성 후 반환 한다")
        @Test
        fun joinLogin() {
            // given
            val loginRequest = LoginRequest("email2@gmail.com", "picture")

            // when
            val loginResponse = authService.login(loginRequest)

            //then
            assertThat(loginResponse.memberId).isNotEqualTo(joinedMember.id)
        }
    }

    @DisplayName("인가를 할 때")
    @Nested
    inner class AuthorizeTest {

        @DisplayName("유효한 토큰이면 인가 응답을 반환한다.")
        @Test
        fun validAuthorize() {
            // given
            val token = tokenProvider.createToken(mutableMapOf("id" to joinedMember.id!!))
            val request = AuthorizationRequest(token)

            // when
            val response = authService.authorize(request)

            // then
            assertAll(
                { assertThat(response.isAuthorized).isTrue() },
                { assertThat(response.memberId).isOne() }
            )
        }

        @DisplayName("유효하지 않은 토큰이면 비인가 응답을 반환한다.")
        @Test
        fun inValidAuthorize() {
            // given
            val request = AuthorizationRequest("inValidToken")

            // when
            val response = authService.authorize(request)

            // then
            assertAll(
                { assertThat(response.isAuthorized).isFalse() },
                { assertThat(response.memberId).isNull() }
            )
        }

        @DisplayName("없는 회원의 토큰이면 비인가 응답을 반환한다.")
        @Test
        fun notExistsMember() {
            // given
            val token = tokenProvider.createToken(mutableMapOf("id" to 2))
            val request = AuthorizationRequest(token)

            // when
            val response = authService.authorize(request)

            // then
            assertAll(
                { assertThat(response.isAuthorized).isFalse() },
                { assertThat(response.memberId).isEqualTo(2) }
            )
        }
    }
}
