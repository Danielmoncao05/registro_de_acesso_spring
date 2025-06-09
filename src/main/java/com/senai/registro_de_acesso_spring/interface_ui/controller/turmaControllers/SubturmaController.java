package com.senai.registro_de_acesso_spring.interface_ui.controller.turmaControllers;

import com.senai.registro_de_acesso_spring.application.dto.turmaDTOs.SubTurmaDTO;
import com.senai.registro_de_acesso_spring.application.service.turmaServices.SubTurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subturmas")
public class SubturmaController {

    @Autowired
    private SubTurmaService service;

    @PostMapping("/{turmaId}")
    public ResponseEntity<Void> criar(@PathVariable Long turmaId) {
        service.criarSubTurma(turmaId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<SubTurmaDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubTurmaDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody SubTurmaDTO dto) {
        if (service.atualizar(id, dto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.deletar(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

