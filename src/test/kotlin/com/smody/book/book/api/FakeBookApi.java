package com.smody.book.book.api;

import java.util.List;

public class FakeBookApi implements BookApi {

    private final List<BookApiResponse> bookApiResponses;

    public FakeBookApi(List<BookApiResponse> bookApiResponses) {
        this.bookApiResponses = bookApiResponses;
    }

    @Override
    public List<BookApiResponse> findAllByTitle(String title) {
        return bookApiResponses;
    }
}
