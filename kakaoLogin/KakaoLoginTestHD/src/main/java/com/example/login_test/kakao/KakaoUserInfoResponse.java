package com.example.login_test.kakao;

import lombok.*;

@Getter
@AllArgsConstructor
public class KakaoUserInfoResponse {
    private final Long id;
    private final KakaoAccount kakao_account;
    private final Properties properties;

    @Getter
    @AllArgsConstructor
    public static class KakaoAccount {
        private final String name;
        private final String email;
        private final String phone_number;
    }

    @Getter
    @AllArgsConstructor
    public static class Properties {
        private final String nickname;
        private final String profile_image;
        private final String thumbnail_image;
    }

}
