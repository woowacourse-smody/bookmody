package com.smody.book.book.api.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class KakaoResponseMeta {

    private Boolean is_end;
    private int pageable_count;
    private int total_count;
}
