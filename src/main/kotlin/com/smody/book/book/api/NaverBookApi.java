package com.smody.book.book.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smody.book.book.api.response.BookApiResponse;
import com.smody.book.book.api.response.NaverApiResponse;
import com.smody.book.book.api.response.NaverBookApiResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NaverBookApi implements BookApi {

    private final String url;
    private final String clientId;
    private final String clientSecret;

    public NaverBookApi(
            @Value("${api.naver.client-id}") String clientId,
            @Value("${api.naver.client-secret}") String clientSecret,
            @Value("${api.naver.url.book.search}") String url) {
        this.url = url;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public List<BookApiResponse> findAllByTitle(String title) {
        ApiClient client = ApiClient.builder()
                .uri(url)
                .addParam("query", title)
                .addHeader("X-Naver-Client-Id", clientId)
                .addHeader("X-Naver-Client-Secret", clientSecret)
                .build();
        ResponseEntity<String> response = client.get();
        return parseToBookResponse(response.getBody());
    }

    private List<BookApiResponse> parseToBookResponse(String responseBody) {
        List<NaverBookApiResponse> naverBookResponses = new ArrayList<>();
        try {
            naverBookResponses = new ObjectMapper().readValue(responseBody, NaverApiResponse.class).getItems();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return naverBookResponses.stream()
                .map(naverBookResponse -> (BookApiResponse) naverBookResponse)
                .collect(Collectors.toList());
    }
}
