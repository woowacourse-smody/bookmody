package com.smody.book.book.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smody.book.book.dto.BookResponse;
import com.smody.book.book.service.BookService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @DisplayName("GET /books?title=토비의 스프링")
    @Test
    void findAllByTitle() throws Exception {
        // given
        List<BookResponse> response = List.of(
                new BookResponse("토비의 스프링", "tobibook.jpg", "토비", "2022-12-31", "토비 출판사", "스프링에 대한 책입니다.")
        );
        given(bookService.findAllByTitle(any(String.class)))
                .willReturn(response);

        // when
        ResultActions result = mockMvc.perform(get("/books")
                .param("title", "토비의 스프링"));

        // then
        result.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
}
