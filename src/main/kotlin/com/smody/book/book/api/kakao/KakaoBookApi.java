package com.smody.book.book.api.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smody.book.book.api.ApiClient;
import com.smody.book.book.api.BookApi;
import com.smody.book.book.api.BookApiResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Primary
@Component
public class KakaoBookApi implements BookApi {

    private final String uri;
    private final String authorization;

    public KakaoBookApi(
            @Value("${api.kakao.uri}") String uri,
            @Value("${api.kakao.authorization}") String authorization) {
        this.uri = uri;
        this.authorization = authorization;
    }

    @Override
    public List<BookApiResponse> findAllByTitle(String title) {
        ApiClient client = ApiClient.builder()
                .uri(uri)
                .addParam("query", title)
                .addHeader("Authorization", authorization)
                .build();

        ResponseEntity<String> response = client.get();
        return parseToBookResponse(response.getBody());
    }

    private List<BookApiResponse> parseToBookResponse(String responseBody) {
        List<KakaoBookApiResponse> kakaoBookApiResponses = new ArrayList<>();
        try {
            kakaoBookApiResponses = new ObjectMapper().readValue(responseBody, KakaoResponse.class).getDocuments();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return kakaoBookApiResponses.stream()
                .map(kakaoBookApiResponse -> (BookApiResponse) kakaoBookApiResponse)
                .collect(Collectors.toList());
    }
}
