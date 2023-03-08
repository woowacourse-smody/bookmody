package com.smody.book.book.service

import com.smody.book.book.api.BookApi
import com.smody.book.book.api.BookApiResponse
import com.smody.book.book.dto.BookResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.RestController

@RestController
class BookService(
    @Qualifier("naver") private val bookApi: BookApi
) {
    fun findAllByTitle(title: String): List<BookResponse> {
        return convertToResponse(bookApi.findAllByTitle(title))
    }

    private fun convertToResponse(apiResponse: List<BookApiResponse>): List<BookResponse> {
        return apiResponse.map { item ->
            BookResponse(
                item.title(), item.image(), item.author(),
                item.pubdate(),
                item.publisher(), item.description()
            )
        }
    }
}
