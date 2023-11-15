package com.example.login_test.kakao;

import com.example.login_test.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class KakaoController {
    private final KakaoService kakaoService;

    private final UserService userService;

    //redirect 경로 mapping
    @GetMapping("/kakao/login")
    public RedirectView kakaoLogin(@RequestParam String code, HttpSession session, HttpServletResponse response) throws IOException {

        System.out.println("code = " + code);

        //추가됨: 카카오 토큰 요청
        KakaoToken kakaoToken = kakaoService.requestToken(code);
        log.info("kakoToken = {}", kakaoToken);

        //추가됨: 유저정보 요청
        KakaoResponse kakaoResponse = kakaoService.requestUser(kakaoToken.getAccess_token());

        log.info("user = {}",kakaoResponse);

        session.setAttribute("access_token", kakaoToken.getAccess_token());

        log.info("토큰: " + String.valueOf(session.getAttribute("access_token")));

        kakaoService.kakaoJoin(response);

        return new RedirectView("/kakao/login");
    }


    @RequestMapping(value="/kakao/logout")
    public RedirectView logout(HttpSession session) {
        String accessToken = (String) session.getAttribute("access_token");
        kakaoService.kakaoLogout(accessToken);
        session.invalidate(); //>> 로그아웃

        return new RedirectView("http://localhost:8080/");
    }

}