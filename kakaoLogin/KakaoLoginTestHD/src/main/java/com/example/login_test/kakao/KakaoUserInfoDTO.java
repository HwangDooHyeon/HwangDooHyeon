package com.example.login_test.kakao;

import lombok.Getter;


@Getter
public class KakaoUserInfoDTO {

    private final String nickname;
    private final String name;
    private final String email;
    private final String profileImage;
    private final String phoneNumber;

    public KakaoUserInfoDTO(String nickname, String name, String email, String profileImage, String phoneNumber) {
            this.nickname = nickname;
            this.name = name;
            this.email = email;
            this.profileImage = profileImage;
            this.phoneNumber = phoneNumber;
    }
}
