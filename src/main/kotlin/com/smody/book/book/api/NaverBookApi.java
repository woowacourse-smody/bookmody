package com.smody.book.book.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smody.book.book.api.response.BookApiResponse;
import com.smody.book.book.api.response.NaverBookApiResponse;
import com.smody.book.book.api.response.NaverApiResponse;
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

    private static final RestTemplate restTemplate = new RestTemplate();

    private final String url;
    private final HttpHeaders headers;


    public NaverBookApi(
            @Value("${api.naver.client-id}") String clientId,
            @Value("${api.naver.client-secret}") String clientSecret,
            @Value("${api.naver.url.book.search}") String url) {
        this.url = url;
        headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);
    }

    @Override
    public List<BookApiResponse> findAllByTitle(String title) {
        String uri = url + "?query=" + title;
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
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
