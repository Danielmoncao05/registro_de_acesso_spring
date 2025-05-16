package com.senai.registro_de_acesso_spring.presentation.controllers;

import com.senai.registro_de_acesso_spring.application.dto.JustificativaDTO;
import com.senai.registro_de_acesso_spring.domain.service.JustificativaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/justificativas")
public class JustificativaController {

    @Autowired
    JustificativaService justificativaService;

    @PostMapping
    public ResponseEntity<Void> registrarJustificativa(@RequestBody JustificativaDTO justificativaDTO) {
             justificativaService.registrarJustificativa(justificativaDTO);
             return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JustificativaDTO> buscarPorId(@PathVariable Long id) {
        return justificativaService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<JustificativaDTO>> listarJustificativa() {
        return ResponseEntity.ok(justificativaService.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarJustificativa(@PathVariable Long id, @RequestBody JustificativaDTO justificativaDTO) {

        if (justificativaService.atualizar(id, justificativaDTO)) {
            return ResponseEntity.ok("Justificativa atualizada!!!!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deletarJustificativa (@PathVariable Long id) {

        if (justificativaService.deletarJustificativa(id)) {
            return ResponseEntity.ok("Justificativa deleta!!!!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
