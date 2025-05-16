package com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers.alunoControllers;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.OcorrenciaDTO;
import com.senai.registro_de_acesso_spring.application.services.usuariosServices.alunoServices.OcorrenciaService;
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
    public ResponseEntity<String> cadastrarOcorrencia(@RequestBody OcorrenciaDTO dto) {
        ocorrenciaService.cadastrarOcorrencia(dto);
        return ResponseEntity.ok("Ocorrência cadastrada com sucesso!"); // return ResponseEntity.ok("Ocorrência do(a) Aluno(a) '" + dto.aluno().getNome() +  "' cadastrada com sucesso!");
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
    public ResponseEntity<String> atualizarOcorrencia(@PathVariable Long id, @RequestBody OcorrenciaDTO dto) {
        if(ocorrenciaService.atualizarOcorrencia(id, dto)) {
            return ResponseEntity.ok("Ocorrência atualizada com sucesso!"); //return ResponseEntity.ok("Ocorrência do(a) Aluno(a) '" + dto.aluno().getNome() +  "' atualizada com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> inativarOcorrencia(@PathVariable Long id) {
        if(ocorrenciaService.inativarOcorrencia(id)) {
            return ResponseEntity.ok("Ocorrência desativada do sistema!");
        }
        return ResponseEntity.notFound().build();
    }

}