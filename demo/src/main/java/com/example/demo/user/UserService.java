package com.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public String findByEmail(String email) {

        User user = userRepository.finByEmail(email);

        if (user == null) {
            return "없는 사용자";
        }

        return "있는 사용자";
    }
}
