package com.senai.registro_de_acesso_spring.presentation.controllers;


import com.senai.registro_de_acesso_spring.application.dto.TurmaDTO;
import com.senai.registro_de_acesso_spring.application.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
    @Autowired
    private TurmaService turmaService;

    @PostMapping
    public ResponseEntity<String> adicionarTurma(@RequestBody TurmaDTO turmaDTO) {
        turmaService.adicionarTurma(turmaDTO);
        return ResponseEntity.ok("Turma adicionada com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<TurmaDTO>> listarTurmas() {
        return ResponseEntity.ok(turmaService.listarTurmas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDTO> buscarPorId(@PathVariable Long id) {
        return turmaService.buscarTurmaPorId(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarPorId(@PathVariable Long id, @RequestBody TurmaDTO turmaDTO) {
        if (turmaService.atualizarTurmaPorId(id, turmaDTO)) {
            return ResponseEntity.ok("Turma atualizada com sucesso!");

        } else {
          return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarTurma(@PathVariable Long id){
        if (turmaService.deletarTurma(id)){
            return ResponseEntity.ok("Turma deletada com sucesso!");
        }else {
            return ResponseEntity.notFound().build();
        }
    }


}
