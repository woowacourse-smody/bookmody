package com.smody.book.book.api.kakao

import com.smody.book.book.api.BookApi
import com.smody.book.book.api.BookApiResponse
import com.smody.book.book.api.ApiClient
import org.springframework.http.ResponseEntity
import com.smody.book.book.api.kakao.KakaoBookApiResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.smody.book.book.api.kakao.KakaoResponse
import com.fasterxml.jackson.core.JsonProcessingException
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Primary
@Component
class KakaoBookApi(
    @param:Value("\${api.kakao.uri}") private val uri: String,
    @param:Value("\${api.kakao.authorization}") private val authorization: String
) : BookApi {
    override fun findAllByTitle(title: String): List<BookApiResponse> {
        val client = ApiClient.builder()
            .uri(uri)
            .addParam("query", title)
            .addHeader("Authorization", authorization)
            .build()
        val response = client.get()
        return parseToBookResponse(response.body)
    }

    private fun parseToBookResponse(responseBody: String?): List<BookApiResponse> {
        var kakaoBookApiResponses: List<KakaoBookApiResponse> = ArrayList()
        try {
            kakaoBookApiResponses =
                ObjectMapper().readValue<KakaoResponse>(responseBody, KakaoResponse::class.java).documents
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }
        return kakaoBookApiResponses.stream()
            .map { kakaoBookApiResponse: KakaoBookApiResponse -> kakaoBookApiResponse }
            .collect(Collectors.toList())
    }
}
