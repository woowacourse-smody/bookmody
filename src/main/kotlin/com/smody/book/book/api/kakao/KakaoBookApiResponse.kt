package com.smody.book.book.api.kakao

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.NoArgsConstructor
import lombok.AllArgsConstructor
import com.smody.book.book.api.BookApiResponse
import lombok.Getter

data class KakaoBookApiResponse(
    @JsonProperty("authors") val authors: List<String>,
    @JsonProperty("contents") val contents: String,
    @JsonProperty("datetime") val datetime: String,
    @JsonProperty("isbn") val isbn: String,
    @JsonProperty("price") val price: Int,
    @JsonProperty("publisher") val publisher: String,
    @JsonProperty("sale_price") val sale_price: Int,
    @JsonProperty("status") val status: String,
    @JsonProperty("thumbnail") val thumbnail: String,
    @JsonProperty("title") val title: String,
    @JsonProperty("translators") val translators: List<String>,
    @JsonProperty("url") val url: String
) : BookApiResponse {
    override fun title(): String {
        return title
    }

    override fun image(): String {
        return thumbnail
    }

    override fun author(): String {
        return authors.toString()
    }

    override fun pubdate(): String {
        return datetime
    }

    override fun publisher(): String {
        return publisher
    }

    override fun description(): String {
        return contents
    }
}
