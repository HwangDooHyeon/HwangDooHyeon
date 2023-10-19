package com.example.demo.user;

import com.example.demo.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

// 생성자를 통해 UserService를 초기화 하는 대신 @RequiredArgsConstructor 어노테이션을 사용
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

//    @RequiredArgsConstructor 어노테이션을 사용하면 생성자를 작성할 필요가 없다.
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @PostMapping("/join") // /join으로 오면 아래 메서드를 실행하란 뜻
   public ResponseEntity<?> login(@RequestBody @Valid UserRequest.LoginDTO requestDTO) {
        // ResponseEntity<?> = JSON으로 변경
        // 변경할 데이터의 형태가 모두 다를 수 있기 때문에 <?> 형태로 반환 (<?> = 와일드카드)
        // @RequestBody = JSON으로 넘어오는 데이터를 UserRequest.LoginDTO 형태로 변경 해주는 역할.
        // @Valid = 받아온 폼의 데이터 유효성을 검사하는 역할을 수행.
        // @RequestBody, @ModelAttribute와 함께 사용한다.
        // DTO에 작성된 @Size, @Pattern, @NotEmpty, ... 을 검사.
        // 필드에 'NOT NULL' 조건이 있거나, 'UNIQUE' 조건이 설정되어 있는 경우도 확인.

//        if (userService.function(loginDTO.getEmail()) == false) {
//            return "존재하지 않는 사용자 이메일입니다.";
//        } 이런 형태로 사용된다.

        // userRepository.save(requestDTO.toEntity());

        return ResponseEntity.ok(ApiUtils.success(null));
//        return ResponseEntity.ok(ApiUtils.error("null"));
   }

}
