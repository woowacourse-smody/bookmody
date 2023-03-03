package com.smody.book.book.api.kakao;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class KakaoResponse {

    private KakaoResponseMeta meta;
    private List<KakaoBookApiResponse> documents;
}
