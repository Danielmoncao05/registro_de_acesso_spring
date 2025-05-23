package com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.ProfessorDTO;
import com.senai.registro_de_acesso_spring.application.service.usuariosServices.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<Void> cadastrarProfessor(@RequestBody ProfessorDTO dto) {
        professorService.cadastrarProfessor(dto);
        return ResponseEntity.ok().build();
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
    public ResponseEntity<Void> atualizarProfessor(@PathVariable Long id, @RequestBody ProfessorDTO dto) {
        if(professorService.atualizarProfessor(id, dto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativarProfessor(@PathVariable Long id) {
        if(professorService.inativarProfessor(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}