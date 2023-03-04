package com.smody.book.book.api.naver

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.smody.book.book.api.ApiClient
import com.smody.book.book.api.BookApi
import com.smody.book.book.api.BookApiResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class NaverBookApi(
    @param:Value("\${api.naver.client-id}") private val clientId: String,
    @param:Value("\${api.naver.client-secret}") private val clientSecret: String,
    @param:Value("\${api.naver.uri}") private val uri: String
) : BookApi {
    override fun findAllByTitle(title: String): List<BookApiResponse> {
        val client = ApiClient.builder()
            .uri(uri)
            .addParam("query", title)
            .addHeader("X-Naver-Client-Id", clientId)
            .addHeader("X-Naver-Client-Secret", clientSecret)
            .build()
        val response = client.get()
        return parseToBookResponse(response.body.toString())
    }

    private fun parseToBookResponse(responseBody: String): List<BookApiResponse> {
        var naverResponseBooks: List<NaverBookApiResponse> = ArrayList()
        try {
            naverResponseBooks = ObjectMapper().readValue(responseBody, NaverResponse::class.java).items
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }
        return naverResponseBooks.stream()
            .map { naverResponseBook: NaverBookApiResponse -> naverResponseBook }
            .collect(Collectors.toList())
    }
}
