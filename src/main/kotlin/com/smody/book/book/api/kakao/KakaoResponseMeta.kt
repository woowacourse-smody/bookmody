package com.smody.book.book.api.kakao

data class KakaoResponseMeta(
    val is_end: Boolean,
    val pageable_count: Int,
    val total_count: Int
)
