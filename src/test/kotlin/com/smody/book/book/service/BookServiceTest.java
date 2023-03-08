package com.smody.book.book.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.smody.book.book.api.FakeBookApi;
import com.smody.book.book.api.FakeBookApiResponse;
import com.smody.book.book.dto.BookResponse;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BookServiceTest {

    private BookService bookService;

    @BeforeEach
    public void setUp() {
        bookService = new BookService(new FakeBookApi(
                List.of(
                        new FakeBookApiResponse("책1", "이미지1.png", "우석", "2023-01-01", "출판사", "설명"),
                        new FakeBookApiResponse("책2", "이미지2.png", "우석", "2023-01-01", "출판사", "설명"),
                        new FakeBookApiResponse("책3", "이미지3.png", "우석", "2023-01-01", "출판사", "설명")
                )
        ));
    }

    @DisplayName("title에 맞는 책을 조회한다.")
    @Test
    void findAll() {
        // when
        List<BookResponse> actual = bookService.findAllByTitle("책");

        // then
        assertThat(actual).hasSize(3);
    }
}
