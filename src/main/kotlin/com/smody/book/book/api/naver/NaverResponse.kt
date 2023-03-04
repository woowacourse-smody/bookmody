package com.smody.book.book.api.naver

import com.fasterxml.jackson.annotation.JsonProperty

data class NaverResponse (
    @JsonProperty("lastBuildDate") val lastBuildDate: String,
    @JsonProperty("total") val total: Int,
    @JsonProperty("start") val start: Int,
    @JsonProperty("display") val display: Int,
    @JsonProperty("items") val items: List<NaverBookApiResponse>
)
