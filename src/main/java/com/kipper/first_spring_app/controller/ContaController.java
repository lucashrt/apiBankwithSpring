package com.kipper.first_spring_app.controller;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.kipper.first_spring_app.repository.ContaRepository;
import com.kipper.first_spring_app.repository.InstituicaoRepository;
import com.kipper.first_spring_app.repository.UserRepository;
import com.kipper.first_spring_app.repository.TransacaoRepository;
import com.kipper.first_spring_app.domain.Conta;
import com.kipper.first_spring_app.domain.Instituicao;
import com.kipper.first_spring_app.domain.User;
import com.kipper.first_spring_app.domain.Transacao;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createConta(
        @RequestParam Long userId, 
        @RequestParam Long instituicaoId, 
        @RequestParam double saldo) {

        Optional<User> user = userRepository.findById(userId);
        Optional<Instituicao> inst = instituicaoRepository.findById(instituicaoId);

        if (user.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        if (inst.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instituição não encontrada.");

        Conta Conta = new Conta();
        Conta.setUser(user.get());
        Conta.setInstituicao(inst.get());
        Conta.setSaldo(saldo);

        Conta novaConta = contaRepository.save(Conta);

        return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
    }

    @GetMapping("/{id}/saldo")
    public ResponseEntity<?> getSaldo(@PathVariable Long id) {
        Optional<Conta> Conta = contaRepository.findById(id);

        if (Conta.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta nao encontrada.");
        }

        return ResponseEntity.ok(Conta.get().getSaldo());
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<?> getExtrato(@PathVariable Long id, @RequestParam String instituicaoName) {
        Optional<Conta> cOptional = contaRepository.findById(id);
        Instituicao instituicao = instituicaoRepository.findByName(instituicaoName);

        if (cOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta nao encontrada.");
        } else if (instituicao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instituição nao encontrada.");
        }

        List<Transacao> transacoes = transacaoRepository.findByContaAndConta_Instituicao(cOptional.get(), instituicao);
        if (transacoes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma transação encontrada para está conta, nesta instituição.");
        } else {
            return ResponseEntity.ok(transacoes);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllContas() {
        List<Conta> contas = contaRepository.findAll();

        if (contas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma conta cadastrada.");
        }

        return ResponseEntity.ok(contas);
    }
}