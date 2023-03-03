package com.smody.book.book.api;

import java.util.List;

public interface BookApi {

    List<BookApiResponse> findAllByTitle(String title);
}
