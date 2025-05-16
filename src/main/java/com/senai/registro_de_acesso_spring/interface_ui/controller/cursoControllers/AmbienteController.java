package com.senai.registro_de_acesso_spring.interface_ui.controller.cursoControllers;

import com.senai.registro_de_acesso_spring.application.dto.cursoDTOs.AmbienteDTO;
import com.senai.registro_de_acesso_spring.application.services.cursoServices.AmbienteService;
import com.senai.registro_de_acesso_spring.domain.entity.curso.Ambiente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ambiente")
public class AmbienteController {
    @Autowired
    AmbienteService ambienteService;

    @PostMapping
    public ResponseEntity<String> cadastrarAmbiente(@RequestBody AmbienteDTO dto) {
        ambienteService.cadastrarAmbiente(dto);
        return ResponseEntity.ok("Ambiente '" + dto.nome() + "' cadastrado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<AmbienteDTO>> listarAmbientes() {
        return ResponseEntity.ok(ambienteService.listarAmbientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AmbienteDTO> buscarAmbientePorId(@PathVariable Long id) {
        return ambienteService.buscarAmbientePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarAmbiente(@PathVariable Long id, @RequestBody AmbienteDTO dto) {
        if(ambienteService.atualizarAmbiente(id, dto)) {
            return ResponseEntity.ok("Ambiente '" + dto.nome() + "' atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> inativarAmbiente(@PathVariable Long id) {
        if(ambienteService.inativarAmbiente(id)) {
            return ResponseEntity.ok("Ambiente desativado do sistema.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}