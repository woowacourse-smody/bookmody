package com.smody.book.book.service;

import com.smody.book.book.api.BookApi;
import com.smody.book.book.api.BookApiResponse;
import com.smody.book.book.dto.BookResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookService {

    private final BookApi bookApi;

    public List<BookResponse> findAllByTitle(String title) {
        return convertToResponse(bookApi.findAllByTitle(title));
    }

    private List<BookResponse> convertToResponse(List<BookApiResponse> apiResponse) {
        return apiResponse.stream()
                .map(item -> new BookResponse(item.title(), item.image(), item.author(),
                        item.pubdate(),
                        item.publisher(), item.description()))
                .collect(Collectors.toList());
    }
}
