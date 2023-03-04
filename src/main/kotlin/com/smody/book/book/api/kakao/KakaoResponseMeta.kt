package com.smody.book.book.api.kakao

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoResponseMeta(
    @JsonProperty("is_end") val is_end: Boolean,
    @JsonProperty("pageable_count") val pageable_count: Int,
    @JsonProperty("total_count") val total_count: Int
)
