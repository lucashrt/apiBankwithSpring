package com.kipper.first_spring_app.repository;

import com.kipper.first_spring_app.domain.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {

    Instituicao findByName(String name);
}