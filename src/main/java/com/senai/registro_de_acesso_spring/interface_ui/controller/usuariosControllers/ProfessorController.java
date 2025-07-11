package com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.ProfessorDTO;
import com.senai.registro_de_acesso_spring.application.service.usuariosServices.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<String> cadastrarProfessor(@RequestBody ProfessorDTO dto) {
        professorService.cadastrarProfessor(dto);
        return ResponseEntity.ok(dto.nome() + " cadastrado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> buscarProfessorPorId(@PathVariable Long id) {
        return  professorService.buscarProfessorPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> listarProfessoresAtivos() {
        return ResponseEntity.ok(professorService.listarProfessores());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarProfessor(@PathVariable Long id, @RequestBody ProfessorDTO dto) {
        if(professorService.atualizarProfessor(id, dto)) {
            return ResponseEntity.ok(dto.nome() + " atualizado com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> inativarProfessor(@PathVariable Long id) {
        if(professorService.inativarProfessor(id)) {
            return ResponseEntity.ok("Professor desativado do sistema!");
        }
        return ResponseEntity.notFound().build();
    }
}