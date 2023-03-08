package com.smody.book.book.api.kakao

data class KakaoResponse(
    val meta: KakaoResponseMeta,
    val documents: List<KakaoBookApiResponse>
)
