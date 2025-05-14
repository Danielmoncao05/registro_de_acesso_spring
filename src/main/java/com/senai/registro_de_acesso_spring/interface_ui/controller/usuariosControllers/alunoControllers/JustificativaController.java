package com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers.alunoControllers;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.JustificativaDTO;
import com.senai.registro_de_acesso_spring.application.services.usuariosServices.alunoServices.JustificativaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/justificativa")
public class JustificativaController {
    @Autowired
    private JustificativaService justificativaService;

    @PostMapping
    public ResponseEntity<Void> cadastrarJustificativa(@RequestBody JustificativaDTO dto) {
        justificativaService.cadastrarJustificativa(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<JustificativaDTO>> listarJustificativas() {
        return ResponseEntity.ok(justificativaService.listarJustificativas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JustificativaDTO> buscarJustificativaPorId(@PathVariable Long id) {
        return justificativaService.buscarJustificativaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarJustificativa(@PathVariable Long id, @RequestBody JustificativaDTO dto) {
        if(justificativaService.atualizarJustificativa(id, dto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativarJustificativa(@PathVariable Long id) {
        if(justificativaService.inativarJustificativa(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}