package com.smody.book.book.api;

import com.smody.book.book.api.response.BookApiResponse;
import java.util.List;

public interface BookApi {

    List<BookApiResponse> findAllByTitle(String title);
}
