package com.smody.book.book.api

interface BookApiResponse {
    fun title(): String
    fun image(): String
    fun author(): String
    fun pubdate(): String
    fun publisher(): String
    fun description(): String
}
