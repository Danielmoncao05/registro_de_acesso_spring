package com.senai.registro_de_acesso_spring.interface_ui.controller.cursoControllers;

import com.senai.registro_de_acesso_spring.application.dto.cursoDTOs.CursoDTO;
import com.senai.registro_de_acesso_spring.application.services.cursoServices.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    CursoService cursoService;

    @PostMapping
    public ResponseEntity<String> cadastrarCurso(@RequestBody CursoDTO dto) {
        cursoService.cadastrarCurso(dto);
        return ResponseEntity.ok("Curso '" + dto.titulo() + "' cadastrado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<CursoDTO>> listarCursos() {
        return ResponseEntity.ok(cursoService.listarCurso());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> buscarCursoPorId(@PathVariable Long id) {
        return cursoService.buscarCursoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarCurso(@PathVariable Long id, @RequestBody CursoDTO dto) {
        if(cursoService.atualizarCurso(id, dto)) {
            return ResponseEntity.ok("Curso '" + dto.titulo() + "' atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> inativarCurso(@PathVariable Long id) {
        if(cursoService.inativarCurso(id)) {
            return ResponseEntity.ok("Curso desativado do sistema.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}