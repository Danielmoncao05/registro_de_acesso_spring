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
    public ResponseEntity<Void> registrarJustificativa(@RequestBody JustificativaDTO dto) {
             justificativaService.registrarJustificativa(dto);
             return ResponseEntity.ok().build();
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
    public ResponseEntity<Void> atualizarJustificativa(@PathVariable Long id, @RequestBody JustificativaDTO dto) {

        if (justificativaService.atualizarJustificativa(id, dto)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deletarJustificativa(@PathVariable Long id) {
        if (justificativaService.deletarJustificativa(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //topico para justificativa de saida antecipada ;

    @PostMapping ("/saida/{idOcorrencia}")
    public ResponseEntity<JustificativaDTO> justificarSaidaAntecipada ( @PathVariable Long idOcorrencia ,   @RequestBody JustificativaDTO dto ) {

        justificativaService.justificarSaidaAntecipada(dto , idOcorrencia);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/saida")
    public ResponseEntity<List<JustificativaDTO>> listarJustificativasDeSaida (@RequestBody JustificativaDTO dto) {

        return ResponseEntity.ok(justificativaService.listarJustificativasSaidas());
    }

    @GetMapping("/saida/{idOcorrencia}")
    public ResponseEntity<JustificativaDTO> buscarJustificativaPorId(@RequestBody JustificativaDTO dto, @PathVariable Long idOcorrencia) {

        return justificativaService.buscarJustificativaPorId(idOcorrencia).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/saida/{idOcorrencia}")
    public ResponseEntity<Void> atualizarJustificativaSaida (@PathVariable Long idOcorrencia , @RequestBody JustificativaDTO dto) {

        if (justificativaService.atualizarJustificativaSaida(idOcorrencia, dto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

   @DeleteMapping("/saida/{idOcorrencia}")
    public ResponseEntity<Void> deletarJustificativaSaida (@PathVariable Long idOcorrencia) {
        if (justificativaService.deletarJustificativaSaida(idOcorrencia)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
   }

}
