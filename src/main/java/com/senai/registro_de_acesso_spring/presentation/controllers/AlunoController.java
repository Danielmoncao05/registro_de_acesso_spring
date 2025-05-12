package com.senai.registro_de_acesso_spring.presentation.controllers;

import com.senai.registro_de_acesso_spring.application.services.usuariosServices.alunoServices.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;
/*
    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody AlunoDTO alunoDTO) {
        alunoService.cadastrar(alunoDTO);
        return ResponseEntity.ok("Aluno cadastrado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listarAlunos() {
        return ResponseEntity.ok(alunoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<AlunoDTO>> buscarPorId(@PathVariable Long id) {
        return alunoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO) {
        if(alunoService.atualizar(id, alunoDTO)) {
            return ResponseEntity.ok("Aluno atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        if(alunoService.deletar(id)) {
            return ResponseEntity.ok("Aluno deletado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // criar salvar e ler ocorrencia | um só de entrada e um só de saida(entrada é automatica) | controller e service pequenos porem uteis | como vou utiliza-lo no aluno ??
*/
}