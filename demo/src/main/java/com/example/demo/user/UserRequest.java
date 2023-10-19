package com.example.demo.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {

    @Getter
    @Setter
    public static class LoginDTO {

        @NotEmpty(message = "이메일 주소를 입력해주세요.")
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "유효한 이메일 주소 형식이 아닙니다.")
        private String email;

        @NotEmpty
        @Size(min = 8, max = 20, message = "8자 이상, 20자 이내로 작성 가능합니다.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "유효한 비밀번호 형식이 아닙니다. (알파벳 대소문자와 숫자만 포함하는 8자 이상)")
        private String password;

        @NotEmpty
        private String userName;

        public User toEntity() {
            return User.builder()
                    .email(email)
                    .password(password)
                    .userName(userName)
                    .build();
        }

    }



}
