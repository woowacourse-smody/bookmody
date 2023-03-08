package com.smody.book.book.api.kakao

import com.smody.book.book.api.BookApiResponse

data class KakaoBookApiResponse(
    val authors: List<String>,
    val contents: String,
    val datetime: String,
    val isbn: String,
    val price: Int,
    val publisher: String,
    val sale_price: Int,
    val status: String,
    val thumbnail: String,
    val title: String,
    val translators: List<String>,
    val url: String
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
