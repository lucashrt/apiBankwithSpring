package com.kipper.first_spring_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kipper.first_spring_app.repository.InstituicaoRepository;
import com.kipper.first_spring_app.domain.Instituicao;

@RestController
@RequestMapping("/instituicao")
public class InstituicaoController {

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createInstituicao(@RequestBody Instituicao instituicao) {
        Instituicao newInstituicao = instituicaoRepository.save(instituicao);
        return ResponseEntity.status(HttpStatus.CREATED).body(newInstituicao);
    }

    @GetMapping("/all")
    public ResponseEntity<?> searchAllInstituicao() {
        List<Instituicao> list = instituicaoRepository.findAll();
        return ResponseEntity.ok(list);
    }
} 