package com.smody.book.security.authentication

import com.fasterxml.jackson.databind.ObjectMapper
import com.smody.book.security.OAuthPrincipal
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.core.Authentication
import java.io.PrintWriter

@Suppress("NonAsciiCharacters")
@ExtendWith(MockitoExtension::class)
class OAuthLoginSuccessHandlerTest {

    private val oAuthLoginSuccessHandler = OAuthLoginSuccessHandler(ObjectMapper())

    @Mock
    private lateinit var request: HttpServletRequest

    @Mock
    private lateinit var response: HttpServletResponse

    @Mock
    private lateinit var writer: PrintWriter

    @Mock
    private lateinit var authentication: Authentication

    @Test
    fun `로그인 성공 후 인증 정보를 HTTP 응답에 쓴다`() {
        // given
        val principal = OAuthPrincipal(1L, "accessToken")
        given(authentication.principal).willReturn(principal)
        given(response.writer).willReturn(writer)

        // when
        oAuthLoginSuccessHandler.onAuthenticationSuccess(request, response, authentication)

        // then
        assertAll(
            { verify(writer).println("{\"memberId\":1,\"accessToken\":\"accessToken\"}") },
            { verify(writer).flush() }
        )
    }
}
