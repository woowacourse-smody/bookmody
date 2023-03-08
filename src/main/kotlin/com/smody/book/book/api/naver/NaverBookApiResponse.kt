package com.smody.book.book.api.naver

import com.smody.book.book.api.BookApiResponse

class NaverBookApiResponse(
    val title: String,
    val link: String,
    val image: String,
    val discount: Int,
    val publisher: String,
    val pubdate: String,
    val author: String,
    val isbn: String,
    val description: String,
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
