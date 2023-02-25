package com.smody.book.book.controller;

import com.smody.book.book.dto.BookResponse;
import com.smody.book.book.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookResponse>> searchBooks(@RequestParam(required = false) String title) {
        List<BookResponse> bookResponses = bookService.findAllByTitle(title);;

        return ResponseEntity.ok(bookResponses);
    }
}
