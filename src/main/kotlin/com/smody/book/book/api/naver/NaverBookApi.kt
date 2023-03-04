package com.smody.book.book.api.naver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smody.book.book.api.ApiClient;
import com.smody.book.book.api.BookApi;
import com.smody.book.book.api.BookApiResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class NaverBookApi implements BookApi {

    private final String uri;
    private final String clientId;
    private final String clientSecret;

    public NaverBookApi(
            @Value("${api.naver.client-id}") String clientId,
            @Value("${api.naver.client-secret}") String clientSecret,
            @Value("${api.naver.uri}") String uri) {
        this.uri = uri;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public List<BookApiResponse> findAllByTitle(String title) {
        ApiClient client = ApiClient.builder()
                .uri(uri)
                .addParam("query", title)
                .addHeader("X-Naver-Client-Id", clientId)
                .addHeader("X-Naver-Client-Secret", clientSecret)
                .build();
        ResponseEntity<String> response = client.get();
        return parseToBookResponse(response.getBody());
    }

    private List<BookApiResponse> parseToBookResponse(String responseBody) {
        List<NaverBookApiResponse> naverResponsBooks = new ArrayList<>();
        try {
            naverResponsBooks = new ObjectMapper().readValue(responseBody, NaverResponse.class).getItems();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return naverResponsBooks.stream()
                .map(naverResponseBook -> (BookApiResponse) naverResponseBook)
                .collect(Collectors.toList());
    }
}
