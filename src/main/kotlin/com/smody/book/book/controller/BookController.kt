package com.smody.book.book.controller

import com.smody.book.book.dto.BookResponse
import com.smody.book.book.service.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(
    private val bookService: BookService
) {
    @GetMapping("/books")
    fun searchBooks(@RequestParam(required = false) title: String): ResponseEntity<List<BookResponse>> {
        val bookResponses = bookService.findAllByTitle(title)
        return ResponseEntity.ok(bookResponses)
    }
}
