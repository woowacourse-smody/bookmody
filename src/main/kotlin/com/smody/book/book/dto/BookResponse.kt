package com.smody.book.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookResponse {

    private String title;
    private String image;
    private String author;
    private String pubdate;
    private String publisher;
    private String description;
}
