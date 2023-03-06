package com.smody.book.auth.support

import jakarta.servlet.http.HttpServletRequest
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpHeaders

@ExtendWith(MockitoExtension::class)
class HttpServletRequestExtensionsKtTest {

    @Mock
    private lateinit var request: HttpServletRequest

    @DisplayName("HTTP 요청에서")
    @Nested
    inner class ExtractBearerTokenTest {

        @DisplayName("Bearer 토큰을 추출할 수 있다.")
        @Test
        fun extract() {
            // given
            given(request.getHeader(HttpHeaders.AUTHORIZATION))
                .willReturn("Bearer valid.access.token")

            // when
            val expected = request.extractBearerToken()

            // then
            assertThat(expected).isEqualTo("valid.access.token")
        }

        @DisplayName("Bearer 형식이 아니면 null을 반환한다.")
        @Test
        fun extractInvalid() {
            // given
            given(request.getHeader(HttpHeaders.AUTHORIZATION))
                .willReturn("valid.access.token")

            // when
            val expected = request.extractBearerToken()

            // then
            assertThat(expected).isNull()
        }
    }
}
