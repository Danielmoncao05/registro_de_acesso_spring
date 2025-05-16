package com.senai.registro_de_acesso_spring.interface_ui.controller.turmaControllers;

import com.senai.registro_de_acesso_spring.application.dto.turmaDTOs.TurmaDTO;
import com.senai.registro_de_acesso_spring.application.services.turmaServices.TurmaService;
import com.senai.registro_de_acesso_spring.domain.entity.turma.Turma;
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
    public ResponseEntity<String> cadastrarTurmas(@RequestBody TurmaDTO dto) {
        turmaService.cadastrarTurma(dto);
        return ResponseEntity.ok("Turma '"+ dto.nome() + "' cadastrada com sucesso!");
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
    public ResponseEntity<String> atualizarTurma(@PathVariable Long id, @RequestBody TurmaDTO dto) {
        if(turmaService.atualizarTurma(id, dto)) {
            return ResponseEntity.ok("Turma '" + dto.nome() + "'atualizada com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> inativarTurma(@PathVariable Long id) {
        if(turmaService.inativarTurma(id)) {
            return ResponseEntity.ok("Turma desativada do sistema.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}