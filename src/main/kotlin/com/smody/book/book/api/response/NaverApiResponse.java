package com.smody.book.book.api.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NaverApiResponse {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<NaverBookApiResponse> items;
}
