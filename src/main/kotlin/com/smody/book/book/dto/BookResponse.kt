package com.smody.book.book.dto

data class BookResponse(
    val title: String,
    val image: String,
    val author: String,
    val pubdate: String,
    val publisher: String,
    val description: String
)
