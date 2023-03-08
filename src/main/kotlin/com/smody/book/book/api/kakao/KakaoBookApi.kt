package com.smody.book.book.api.kakao

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.smody.book.book.api.ApiClient
import com.smody.book.book.api.BookApi
import com.smody.book.book.api.BookApiResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component("kakao")
class KakaoBookApi(
    private val objectMapper: ObjectMapper,
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
                objectMapper.readValue(responseBody, KakaoResponse::class.java).documents
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }
        return kakaoBookApiResponses
    }
}
