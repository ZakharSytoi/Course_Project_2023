package com.example.course_project_2023.repository.daos;


import com.example.course_project_2023.repository.model.SecurityUser;
import com.example.course_project_2023.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNickname(String nickname);
}
