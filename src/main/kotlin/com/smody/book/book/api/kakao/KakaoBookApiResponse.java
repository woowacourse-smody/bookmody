package com.smody.book.book.api.kakao;

import com.smody.book.book.api.BookApiResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class KakaoBookApiResponse implements BookApiResponse {

    private List<String> authors;
    private String contents;
    private String datetime;
    private String isbn;
    private int price;
    private String publisher;
    private int sale_price;
    private String status;
    private String thumbnail;
    private String title;
    private List<String> translators;
    private String url;

    @Override
    public String title() {
        return title;
    }

    @Override
    public String image() {
        return thumbnail;
    }

    @Override
    public String author() {
        return authors.toString();
    }

    @Override
    public String pubdate() {
        return datetime;
    }

    @Override
    public String publisher() {
        return publisher;
    }

    @Override
    public String description() {
        return contents;
    }
}
