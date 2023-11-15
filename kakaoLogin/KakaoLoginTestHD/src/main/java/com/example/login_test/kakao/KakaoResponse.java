package com.example.login_test.kakao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class KakaoResponse {
    private Long id;
    private String email;
    private String username;
    private String phoneNumber;
    private String provider;
    private String properties;
}
