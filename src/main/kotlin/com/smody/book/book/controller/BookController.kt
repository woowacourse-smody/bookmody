package com.smody.book.book.controller

import org.springframework.web.bind.annotation.RestController
import lombok.RequiredArgsConstructor
import com.smody.book.book.service.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.http.ResponseEntity
import com.smody.book.book.dto.BookResponse

@RestController
@RequiredArgsConstructor
class BookController (
    private val bookService: BookService
) {
    @GetMapping("/books")
    fun searchBooks(@RequestParam(required = false) title: String): ResponseEntity<List<BookResponse>> {
        val bookResponses = bookService.findAllByTitle(title)
        return ResponseEntity.ok(bookResponses)
    }
}
