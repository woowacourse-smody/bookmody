package com.smody.book.book.api;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ApiClient {

    private static final RestTemplate restTemplate = new RestTemplate();

    private final HttpEntity<Object> httpEntity;
    private final String url;

    public ResponseEntity<String> get() {
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final HttpHeaders headers = new HttpHeaders();
        private final Map<String, String> params = new HashMap<>();

        private String uri;

        public Builder uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder addHeader(String key, String value) {
            headers.set(key, value);
            return this;
        }

        public Builder addParam(String key, String value) {
            params.put(key, value);
            return this;
        }

        public ApiClient build() {
            return new ApiClient(this);
        }
    }

    private ApiClient(Builder builder) {
        this.httpEntity = new HttpEntity<>(builder.headers);
        this.url = builder.uri + convertParams(builder.params);
    }

    private String convertParams(Map<String, String> params) {
        return params.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .reduce("?", (a, b) -> a + b);
    }
}
