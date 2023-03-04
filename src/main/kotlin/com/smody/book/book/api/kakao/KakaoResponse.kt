package com.smody.book.book.api.kakao

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoResponse (
    @JsonProperty("meta") val meta: KakaoResponseMeta,
    @JsonProperty("documents") val documents: List<KakaoBookApiResponse>
)
