package com.kipper.first_spring_app.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kipper.first_spring_app.repository.ContaRepository;
import com.kipper.first_spring_app.repository.TransacaoRepository;
import com.kipper.first_spring_app.domain.Transacao;
import com.kipper.first_spring_app.domain.Conta;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createTransacao(@RequestBody Transacao transacao) {
        Optional<Conta> contaOptional = contaRepository.findById(transacao.getConta().getId());

        if (contaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta com ID " + transacao.getConta().getId() + " nao encontrada.");
        }

        Conta conta = contaOptional.get();

        if (transacao.getTipo().equalsIgnoreCase("saque") && conta.getSaldo() < transacao.getValor()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo insuficiente.");
        }

        if (transacao.getTipo().equalsIgnoreCase("deposito")) {
            conta.setSaldo(conta.getSaldo() + transacao.getValor());
        } else if (transacao.getTipo().equalsIgnoreCase("saque")) {
            conta.setSaldo(conta.getSaldo() - transacao.getValor());
        }

        Transacao transacaoDB = new Transacao();
        transacaoDB.setTipo(transacao.getTipo());
        transacaoDB.setValor(transacao.getValor());
        transacaoDB.setData(LocalDateTime.now());
        transacaoDB.setConta(conta);

        transacaoRepository.save(transacaoDB);
        contaRepository.save(conta);

        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoDB);
    }
}
