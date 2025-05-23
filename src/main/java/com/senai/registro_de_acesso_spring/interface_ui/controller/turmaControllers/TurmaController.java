package com.senai.registro_de_acesso_spring.interface_ui.controller.turmaControllers;

import com.senai.registro_de_acesso_spring.application.dto.turmaDTOs.TurmaDTO;
import com.senai.registro_de_acesso_spring.application.service.turmaServices.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turma")
public class TurmaController {
    @Autowired
    TurmaService turmaService;

    @PostMapping
    public ResponseEntity<Void> cadastrarTurmas(@RequestBody TurmaDTO dto) {
        turmaService.cadastrarTurma(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<TurmaDTO>> listarTurmas() {
        return ResponseEntity.ok(turmaService.listarTurmas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDTO> buscarTurmaPorId(@PathVariable Long id) {
        return turmaService.buscarTurmaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarTurma(@PathVariable Long id, @RequestBody TurmaDTO dto) {
        if(turmaService.atualizarTurma(id, dto)) return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativarTurma(@PathVariable Long id) {
        if(turmaService.deletarTurma(id)) return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }
}