package com.kipper.first_spring_app.repository;

import java.util.List;

import com.kipper.first_spring_app.domain.Conta;
import com.kipper.first_spring_app.domain.Instituicao;
import com.kipper.first_spring_app.domain.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findByContaAndConta_Instituicao(Conta conta, Instituicao instituicao);
}