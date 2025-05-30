package com.senai.registro_de_acesso_spring.interface_ui.controller.cursoControllers;

import com.senai.registro_de_acesso_spring.application.dto.cursoDTOs.CursoDTO;
import com.senai.registro_de_acesso_spring.application.service.cursoServices.CursoService;
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
    public ResponseEntity<Void> cadastrarCurso(@RequestBody CursoDTO dto) {
        cursoService.cadastrarCurso(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CursoDTO>> listarCursos() {
        cursoService.listarCursos();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> buscarCursoPorId(@PathVariable Long id) {
        return cursoService.buscarCursoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarCurso(@PathVariable Long id, @RequestBody CursoDTO dto) {
        try {
            cursoService.atualizarCurso(id, dto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCurso(@PathVariable Long id) {
        cursoService.deletarCurso(id);
        return ResponseEntity.noContent().build();
    }
}