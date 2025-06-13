package com.senai.registro_de_acesso_spring.interface_ui.controller;

import com.senai.registro_de_acesso_spring.application.dto.OcorrenciaDTO;
import com.senai.registro_de_acesso_spring.application.services.OcorrenciaService;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    public void processarMqtt(String mT){
        ocorrenciaService.criarOcorrenciaDeAtraso(mT);

    }
    @PostMapping("/saida/{idAluno}")
   public void solicitarSaida( @PathVariable Long idAluno, OcorrenciaDTO dto){
        ocorrenciaService.criarOcorrenciaDeSaida(idAluno,dto);
    }

    @GetMapping
    public ResponseEntity<List<OcorrenciaDTO>> listarOcorrenciaDeSaida(@PathVariable Aluno aluno){
        return ResponseEntity.ok(ocorrenciaService.listarOcorrenciasDeSaidaPorIdDeAluno(aluno));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<OcorrenciaDTO> buscarPorId(@PathVariable Long id){
        return ocorrenciaService.buscarOcorrenciaSaidaPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarPorId(@PathVariable Long id, @RequestBody OcorrenciaDTO ocorrenciaDTO){
        if (ocorrenciaService.atualizarOcorrenciaSaidaPorId(id,ocorrenciaDTO)){
            return ResponseEntity.ok("Ocorrencia atualizada!");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        if (ocorrenciaService.deletarOcorrenciaSaida(id)){
            return ResponseEntity.ok("Ocorrencia deletada!");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    }



