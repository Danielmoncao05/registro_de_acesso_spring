package com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers.alunoControllers;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.OcorrenciaDTO;
import com.senai.registro_de_acesso_spring.application.service.usuariosServices.alunoServices.OcorrenciaService;
import com.senai.registro_de_acesso_spring.domain.service.ocorrenciaService.OcorrenciaServiceDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ocorrencia")
public class OcorrenciaController {
    @Autowired
    private OcorrenciaService ocorrenciaService;

    @Autowired
    private OcorrenciaServiceDomain ocorrenciaServiceDomain;

    @PostMapping//("/{id}") // por que passar o id? (não me recordo)
    public ResponseEntity<String> registrarOcorrencia(@RequestBody OcorrenciaDTO dto) {
        ocorrenciaService.registrarOcorrencia(dto);
        return ResponseEntity.ok("Ocorrência registrada com sucesso!");
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
            return ResponseEntity.ok("Ocorrência atualizada com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarOcorrencia(@PathVariable Long id) {
        if(ocorrenciaService.deletarOcorrencia(id)) {
            return ResponseEntity.ok("Ocorrência deletada do sistema!");
        }
        return ResponseEntity.notFound().build();
    }

    // Ocorrencia Service Domain
    // Criar Ocorrencia De Atraso e Processar Mqtt
    public void criarOcorrenciaAtraso(String idDeAcesso) {
        System.out.println(idDeAcesso);
        ocorrenciaServiceDomain.criarOcorrenicaDeAtraso(idDeAcesso);
    }

    // ToDo: Processo de Ocorrência de Atraso

    // Processo de Ocorrência de Saida Antecipada
    @MessageMapping("/ocorrencia/saida")
    public void solicitarSaida(@Payload OcorrenciaDTO dto) {
        ocorrenciaServiceDomain.solicitarSaidaAntecipada(dto);
    }

    @MessageMapping("/ocorrencia/decisao")
    public void decidirSaida(@Payload OcorrenciaDTO dto) {
        ocorrenciaServiceDomain.decidirSaida(dto);
    }

    @MessageMapping("/ocorrencia/ciencia")
    public void darCiencia(@Payload OcorrenciaDTO dto) {
        ocorrenciaServiceDomain.confirmarCiencia(dto);
    }

}