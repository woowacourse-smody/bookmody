package com.smody.book.book.service

import org.springframework.web.bind.annotation.RestController
import lombok.RequiredArgsConstructor
import com.smody.book.book.api.BookApi
import com.smody.book.book.dto.BookResponse
import com.smody.book.book.api.BookApiResponse
import java.util.stream.Collectors

@RestController
@RequiredArgsConstructor
class BookService (
        private val bookApi: BookApi
) {
    fun findAllByTitle(title: String): List<BookResponse> {
        val convertToResponse = convertToResponse(bookApi.findAllByTitle(title))
        return convertToResponse
    }


    private fun convertToResponse(apiResponse: List<BookApiResponse>): List<BookResponse> {
        return apiResponse.stream()
            .map { item: BookApiResponse ->
                BookResponse(
                    item.title(), item.image(), item.author(),
                    item.pubdate(),
                    item.publisher(), item.description()
                )
            }
            .collect(Collectors.toList())
    }
}
