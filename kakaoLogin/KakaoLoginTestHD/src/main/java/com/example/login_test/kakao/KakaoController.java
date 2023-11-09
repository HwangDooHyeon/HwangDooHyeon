package com.example.login_test.kakao;

import com.example.login_test.user.UserRequest;
import com.example.login_test.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Slf4j
public class KakaoController {

    private final KakaoService kakaoService;
    private final UserService userService;

    @Autowired
    public KakaoController(KakaoService kakaoService, UserService userService) {
        this.kakaoService = kakaoService;
        this.userService = userService;
    }

//    @GetMapping("/kakao/login")
//    public ResponseEntity<?> kakaoLogin(@RequestParam(value = "code", required = false) String code) throws JsonProcessingException {
//        if (code == null) {
//            // 인증 코드가 전달되지 않았다면 로그인 URL을 반환
//            String loginUrl = kakaoService.getKakaoLogin();
//            return ResponseEntity.ok().body(loginUrl);
//        } else {
//            // 카카오 로그인 페이지에서
//            // 인증 코드가 전달 되었다면 액세스 토큰을 얻음
//            //(토큰 얻는 과정 = 서비스 클래스의 겟토큰 메서드가 먼저 이뤄짐 == 여기는 만들어진 토큰을 겟)
//            String accessToken;
//            try {
//                accessToken = kakaoService.getKakaoToken(code);
//            } catch (Exception e) {
//                return ResponseEntity.status(500).body(e.getMessage());
//            }
//            System.out.println("access_token : " + accessToken);
//
//            // 서비스클래스에서 가져온 카카오인포 값을 유저인포디티오에 넣어줌
//            KakaoUserInfoDTO userInformationDTO = kakaoService.getKakaoInfo(accessToken);
//
//            // 응답에 액세스 토큰과 유저 정보를 함께 반환하기 위해서 객체 지정
//
//            // 객체 형태로 넣어주기 위해서 사용, 반환값이 ResponseEntity 였기 때문에 String 형식 불가했음
//            // 토큰을 먼저 출력하고 싶은데 그냥 map 사용시 순서가 정해져 있지 않아서 링크드 해시맵 사용
//            // 단, 보통 순서가 중요하지 않기 때문에 맵을 많이 사용함 , 메모리 사용도 덜 하기 때문.
//            Map<String, Object> responseData = new LinkedHashMap<>();
//            responseData.put("accessToken", accessToken);
//            responseData.put("userInformation", userInformationDTO);
//
//            // 응답이 성공적이라면 (200) 데이터 값 반환
//            return ResponseEntity.ok(responseData);
//        }
//    }

    @GetMapping("/kakao/login")
    public ResponseEntity<?> kakao_login(@RequestParam(value = "code", required = false) String code) throws JsonProcessingException {
        if (code == null) {
            // 인증 코드가 전달되지 않았다면 로그인 URL을 반환
            String loginUrl = kakaoService.getKakaoLogin();
            return ResponseEntity.ok().body(loginUrl);
        } else {
            // 카카오 로그인 페이지에서
            // 인증 코드가 전달 되었다면 액세스 토큰을 얻음
            //(토큰 얻는 과정 = 서비스 클래스의 겟토큰 메서드가 먼저 이뤄짐 == 여기는 만들어진 토큰을 겟)
            String accessToken;
            try {
                accessToken = kakaoService.getKakaoToken(code);
            } catch (Exception e) {
                return ResponseEntity.status(500).body(e.getMessage());
            }
            System.out.println("access_token : " + accessToken);

            // 서비스클래스에서 가져온 카카오인포 값을 유저인포디티오에 넣어줌
            KakaoUserInfoDTO userInformation = kakaoService.getKakaoInfo(accessToken);

            // 응답에 액세스 토큰과 유저 정보를 함께 반환하기 위해서 객체 지정

            // 객체 형태로 넣어주기 위해서 사용, 반환값이 ResponseEntity 였기 때문에 String 형식 불가했음
            // 토큰을 먼저 출력하고 싶은데 그냥 map 사용시 순서가 정해져 있지 않아서 링크드 해시맵 사용
            // 단, 보통 순서가 중요하지 않기 때문에 맵을 많이 사용함 , 메모리 사용도 덜 하기 때문.
            Map<String, Object> responseData = new LinkedHashMap<>();
            responseData.put("accessToken", accessToken);
            responseData.put("userInformation", userInformation);

            // 응답이 성공적이라면 (200) 데이터 값 반환
            return ResponseEntity.ok(responseData);
        }
    }
}
