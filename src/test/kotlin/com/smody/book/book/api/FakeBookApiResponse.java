package com.smody.book.book.api;

public class FakeBookApiResponse implements BookApiResponse {

    private final String title;
    private final String image;
    private final String author;
    private final String pubdate;
    private final String publisher;
    private final String description;

    public FakeBookApiResponse(String title, String image, String author, String pubdate, String publisher,
                               String description) {
        this.title = title;
        this.image = image;
        this.author = author;
        this.pubdate = pubdate;
        this.publisher = publisher;
        this.description = description;
    }

    @Override
    public String title() {
        return null;
    }

    @Override
    public String image() {
        return null;
    }

    @Override
    public String author() {
        return null;
    }

    @Override
    public String pubdate() {
        return null;
    }

    @Override
    public String publisher() {
        return null;
    }

    @Override
    public String description() {
        return null;
    }
}
