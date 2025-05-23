package com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers.alunoControllers;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.OcorrenciaDTO;
import com.senai.registro_de_acesso_spring.application.service.usuariosServices.alunoServices.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ocorrencia")
public class OcorrenciaController {
    @Autowired
    private OcorrenciaService ocorrenciaService;

    @PostMapping
    public ResponseEntity<Void> registrarOcorrencia(@RequestBody OcorrenciaDTO dto) {
        ocorrenciaService.registrarOcorrencia(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<OcorrenciaDTO>> listarOcorrencias() {
        return ResponseEntity.ok(ocorrenciaService.listarOcorrencia());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciaDTO> buscarOcorrenciaPorID(@PathVariable Long id) {
        return ocorrenciaService.buscarOcorrenciaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarOcorrencia(@PathVariable Long id, @RequestBody OcorrenciaDTO dto) {
        if(ocorrenciaService.atualizarOcorrencia(id, dto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOcorrencia(@PathVariable Long id) {
        if(ocorrenciaService.deletarOcorrencia(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Criar Ocorrencia De Atraso e Processar Mqtt
    public void criarOcorrenciaAtraso(String idDeAcesso) {
        System.out.println(idDeAcesso);
        ocorrenciaService.criarOcorrenicaDeAtraso(idDeAcesso);
    }

}