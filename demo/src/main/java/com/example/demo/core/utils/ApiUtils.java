package com.example.demo.core.utils;

import lombok.Getter;
import lombok.Setter;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ApiUtils {

    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<T>(true, response, null);
    }


    public static <T> ApiResult<T> error(String message) {
        return new ApiResult<T>(false, null, new ApiError(message));
    }




    // JSON으로 반환해야할 데이터
    @AllArgsConstructor // 생성자 자동 생성
    @Getter
    @Setter
    public static class ApiResult<T> {
        private final boolean success; // 현재 상태
        private final T response; // 반환할 실제 데이터
        private final ApiError error;

//        public ApiResult(boolean success, T response, ApiError error) {
//            this.success = success;
//            this.response = response;
//            this.error = error;
//        }

        public String eString() {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append("success", success)
                    .append("response", response)
                    .append("error", error)
                    .toString();
        }
    }




    @AllArgsConstructor // 생성자 자동 생성
    @Getter
    @Setter
    public static class ApiError {
        private String message;

//        public ApiError(String message) {
//            this.message = message;
//        }

        public String eString() {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append("message", message)
                    .toString();
        }
    }


}
