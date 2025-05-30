package com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers.alunoControllers;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.JustificativaDTO;
import com.senai.registro_de_acesso_spring.application.service.usuariosServices.alunoServices.JustificativaService;
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
    public ResponseEntity<String> registrarJustificativa(@RequestBody JustificativaDTO dto) {
             justificativaService.registrarJustificativa(dto);
             return ResponseEntity.ok("Justificativa registrada com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<JustificativaDTO>> listarJustificativa() {
        return ResponseEntity.ok(justificativaService.listarJustificativas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JustificativaDTO> buscarPorId(@PathVariable Long id) {
        return justificativaService.buscarJustificativaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarJustificativa(@PathVariable Long id, @RequestBody JustificativaDTO dto) {
        if (justificativaService.atualizarJustificativa(id, dto)) {
            return ResponseEntity.ok("Justificativa atualizada com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deletarJustificativa(@PathVariable Long id) {
        if (justificativaService.deletarJustificativa(id)) {
            return ResponseEntity.ok("Justificativa deletada do sistema!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
