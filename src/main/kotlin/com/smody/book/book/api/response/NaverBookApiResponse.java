package com.smody.book.book.api.response;

import lombok.Getter;

@Getter
public class NaverBookApiResponse implements BookApiResponse {

    private String title;
    private String link;
    private String image;
    private int discount;
    private String publisher;
    private String pubdate;
    private String author;
    private String isbn;
    private String description;

    @Override
    public String title() {
        return title;
    }

    @Override
    public String image() {
        return image;
    }

    @Override
    public String author() {
        return author;
    }

    @Override
    public String pubdate() {
        return pubdate;
    }

    @Override
    public String publisher() {
        return publisher;
    }

    @Override
    public String description() {
        return description;
    }
}
