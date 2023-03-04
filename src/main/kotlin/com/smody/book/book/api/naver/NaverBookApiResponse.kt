package com.smody.book.book.api.naver

import com.fasterxml.jackson.annotation.JsonProperty
import com.smody.book.book.api.BookApiResponse

class NaverBookApiResponse(
    @JsonProperty("title") val title: String,
    @JsonProperty("link") val link: String,
    @JsonProperty("image") val image: String,
    @JsonProperty("discount") val discount: Int,
    @JsonProperty("publisher") val publisher: String,
    @JsonProperty("pubdate") val pubdate: String,
    @JsonProperty("author") val author: String,
    @JsonProperty("isbn") val isbn: String,
    @JsonProperty("description") val description: String,
) : BookApiResponse {

    override fun title(): String {
        return title
    }

    override fun image(): String {
        return image
    }

    override fun author(): String {
        return author
    }

    override fun pubdate(): String {
        return pubdate
    }

    override fun publisher(): String {
        return publisher
    }

    override fun description(): String {
        return description
    }
}
