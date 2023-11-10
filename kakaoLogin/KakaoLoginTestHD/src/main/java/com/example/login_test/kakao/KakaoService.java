package com.example.login_test.kakao;

import com.example.login_test.core.error.exception.Exception500;
import com.example.login_test.user.UserRepository;
import com.example.login_test.user.UserRequest;
import com.example.login_test.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoService {

    @Value("${kakao.auth.uri}")
    private String KAKAO_AUTH_URI;

    @Value("${kakao.api.key}")
    private String API_KEY;

    @Value("${kakao.redirect.uri}")
    private String REDIRECT_URI;

    @Value("${kakao.secret.key}")
    private String Client_Secret;

    // 컨롤에서 이 메서드를 호출
    // 카카오 로그인 페이지로 리다이렉트 하기 위한 URL 생성(return 카카오 로그인 페이지로)
    public String getKakaoLogin() {
        return KAKAO_AUTH_URI + "/authorize"
                + "?client_id=" + API_KEY
                + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=code";
    }

    // 코드를 가져와서 카카오 토큰을 가져오는 메서드
    public String getKakaoToken(String code) {
        // 인증 코드가 없으면 예외를 발생 시킴
        if (code == null) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Failed get authorization code");

        // HTTP 헤더 객체 생성 후 헤더에 컨텐트 타입과 벨류 값 저장
        // 키 값 - 타입 , 밸류 - 요청 본문이 url 인코딩된 형태로 전송되어야 하고 UTF-8문자 사용
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // MultiValueMap 하나의 키에 여러개의 값을 연결할 수 있음.
        // LinkedMultiValueMap 인스턴스를 생성하여 params에 저장
        // 각각의 "키", "값" 쌍을 추가
        // 맵에 저장된 파라미터가 HTTP요청에 포함 됨
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type"   , "authorization_code");
        params.add("client_id"    , API_KEY);
        params.add("redirect_uri" , REDIRECT_URI);
        params.add("code"         , code);
        params.add("client_secret", Client_Secret);

        //params에 설정된 파라미터와 headers에 설정된 헤더를 포함하는 HTTP 요청을 생성하고,
        // 이를 RestTemplate을 통해 보내는 작업을 준비

        RestTemplate restTemplate = new RestTemplate();

        // HttpEntity 생성자에서 (R body,MultiValueMap<String, String> headers)
        // 이므로 순서는 무조건 (본문,헤더) 여야함 즉 (params, headers) 순서 바뀌면 안됨
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

        // RestTemplate의 exchange 메서드를 사용하여 HTTP 요청을 보내고, 그 응답을 ResponseEntity 객체로 받는 부분
        //KAKAO_AUTH_URI + "/token" URL로 POST 요청을 보내고, 그 응답을 String 타입의 ResponseEntity 객체로 받는 것
        //이 요청에는 httpEntity에 설정된 본문과 헤더가 포함
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(KAKAO_AUTH_URI + "/token", // 수정된 부분
                        HttpMethod.POST,
                        httpEntity,
                        String.class);

        //서버로부터 받은 HTTP 응답을 확인
        // HTTP 응답의 본문을 JSON 객체로 변환
        // responseEntity.getBody()는 HTTP 응답의 본문을 반환
        // gson.fromJson(...)은 이 본문을 JsonObject로 변환
        // 변환된 JSON 객체에서 "access_token" 키의 값을 문자열로 가져와서 반환. (카카오 서버에서 발급한)
        // 응답이 성공적인 경우 응답 본문에서 액세스 토큰을 추출하여 반환
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseEntity.getBody(), JsonObject.class);

            return jsonObject.get("access_token").getAsString();
        } else {
            throw new HttpClientErrorException(responseEntity.getStatusCode(), "Failed to get access token");
        }
    }

    //액세스 토큰으로 카카오 API를 통해 사용자 정보를 가져옴
    public KakaoUserInfoDTO getKakaoInfo(String accessToken) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();

        // "Authorization" 헤더: 클라이언트가 서버에게 자신의 신원을 증명 하는데 사용
        // Bearer " 뒤에 액세스 토큰을 붙여서 이 헤더 값을 설정: 클라이언트가 이 액세스 토큰을 가지고 있음을 서버에 알리는 것
        httpHeaders.add("Authorization", "Bearer " + accessToken);

        // 이 요청에는 본문이 필요없어서 헤더만 사용(가능)
        // 보통은 (본문,헤더) 가 일반적임
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> responseEntity =
                restTemplate.exchange("https://kapi.kakao.com/v2/user/me",
                        HttpMethod.POST,
                        entity,
                        String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {

            Gson gson = new Gson();

            // kakaoUserInforResponse 객체로 변환
//            KakaoUserInfoResponse kakaoUserInfoResponse = gson.fromJson(responseEntity.getBody(), KakaoUserInfoResponse.class);
            UserRequest.KakaoDTO kakaoDTO = gson.fromJson(responseEntity.getBody(), UserRequest.KakaoDTO.class);

            // kakaoUserInforResponse 객체에서 값을 가져오기
            String name = kakaoDTO.getKakao_account().getName();
            String phone_number = kakaoDTO.getKakao_account().getPhone_number();
            String email = kakaoDTO.getKakao_account().getEmail();
            String nickname = kakaoDTO.getProperties().getNickname();
            String profile_image = kakaoDTO.getProperties().getProfile_image();

            System.out.println(name);
            System.out.println(phone_number);
            System.out.println(email);
            System.out.println(nickname);
            System.out.println(profile_image);

            //가져온 정보를 KakaoUserInforDto 객체에 담아 반환
            return new KakaoUserInfoDTO(name, phone_number, email, nickname, profile_image);

        } else {
            throw new HttpClientErrorException(responseEntity.getStatusCode(), "Failed to get user info");
        }
    }

}
