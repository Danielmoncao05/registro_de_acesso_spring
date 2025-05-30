package com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers.alunoControllers;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.OcorrenciaDTO;
import com.senai.registro_de_acesso_spring.application.service.usuariosServices.alunoServices.OcorrenciaService;
import com.senai.registro_de_acesso_spring.domain.service.OcorrenciaService.OcorrenciaServiceDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ocorrencia")
public class OcorrenciaController {
    @Autowired
    private OcorrenciaService ocorrenciaService;

    @Autowired
    private OcorrenciaServiceDomain ocorrenciaServiceRN;

    /*
    @PostMapping("/{id}")
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
    }*/

    public void verificarAcesso(String idAcesso) {
        ocorrenciaService.gerarOcorrenciaDeAtraso(idAcesso);
    }

    /*
    @MessageMapping("/ocorrencia/saida")
    public void solicitarSaida(@Payload OcorrenciaDTO dto) {
        ocorrenciaServiceRN.solicitarSaidaAntecipada(dto);
    }

    @MessageMapping("/ocorrencia/decisao")
    public void decidirSaida(@Payload OcorrenciaDTO dto) {
        ocorrenciaServiceRN.decidirSaida(dto);
    }

    @MessageMapping("/ocorrencia/ciencia")
    public void darCiencia(@Payload OcorrenciaDTO dto) {
        ocorrenciaServiceRN.confirmarCiencia(dto);
    }
     */

}