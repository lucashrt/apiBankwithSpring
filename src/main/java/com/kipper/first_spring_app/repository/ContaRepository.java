package com.kipper.first_spring_app.repository;

import com.kipper.first_spring_app.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}