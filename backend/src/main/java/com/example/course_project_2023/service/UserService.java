package com.example.course_project_2023.service;

import com.example.course_project_2023.repository.daos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    boolean existsWithNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }
}
