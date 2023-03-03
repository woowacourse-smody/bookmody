package com.smody.book.book.api.naver;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NaverResponse {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<NaverBookApiResponse> items;
}
