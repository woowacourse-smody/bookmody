package com.smody.book.book.api

interface BookApi {
    fun findAllByTitle(title: String): List<BookApiResponse>
}
