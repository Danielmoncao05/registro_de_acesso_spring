package com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers.alunoControllers;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.AlunoDTO;
import com.senai.registro_de_acesso_spring.application.service.usuariosServices.alunoServices.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class    AlunoController {
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<String> cadastrarAluno(@RequestBody AlunoDTO alunoDTO) {
        alunoService.cadastrarAluno(alunoDTO);
        return ResponseEntity.ok(alunoDTO.nome() + " cadastrado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listarAlunos() {
        return ResponseEntity.ok(alunoService.listarAlunos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> buscarAlunoPorId(@PathVariable Long id) {
        return alunoService.buscarAlunoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarAluno(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO) {
        if(alunoService.atualizarAluno(id, alunoDTO)) {
            return ResponseEntity.ok(alunoDTO.nome() + " atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> inativarAluno(@PathVariable Long id) {
        if(alunoService.inativarAluno(id)) {
            return ResponseEntity.ok("Aluno desativado do sistema!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}