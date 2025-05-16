package com.senai.registro_de_acesso_spring.presentation.controllers;

import com.senai.registro_de_acesso_spring.application.dto.AlunoDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody AlunoDTO alunoDTO) {
        alunoService.cadastrar(alunoDTO);
        return ResponseEntity.ok(alunoDTO.nome() + " cadastrado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listarAlunos() {
        return ResponseEntity.ok(alunoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> buscarPorId(@PathVariable Long id) {
        return alunoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO) {
        if(alunoService.atualizar(id, alunoDTO)) {
            return ResponseEntity.ok(alunoDTO.nome() + " atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> desativar(@PathVariable Long id) {
        if(alunoService.desativar(id)) {
            return ResponseEntity.ok("Aluno desativado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}