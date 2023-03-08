package com.smody.book.book.api.naver

import com.fasterxml.jackson.annotation.JsonProperty

data class NaverResponse(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<NaverBookApiResponse>
)
