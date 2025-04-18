package com.kipper.first_spring_app.repository;

import com.kipper.first_spring_app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsByCPF(String CPF);
}