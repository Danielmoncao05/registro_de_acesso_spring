package com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers.alunoControllers;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.JustificativaDTO;
import com.senai.registro_de_acesso_spring.application.service.usuariosServices.alunoServices.JustificativaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/justificativa")
public class JustificativaController {
    @Autowired
    private JustificativaService justificativaService;

    // CRUD de Justificativa
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
    public ResponseEntity<JustificativaDTO> buscarJustificativaPorId(@PathVariable Long id) {
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

    // Justificativa Service Domain - TESTE
    public void testeJustificativaFaltaIdAcesso(String idAcesso) {
        System.out.println("Justificativa - idAcesso: " + idAcesso);
    }

    // Processo de Justificativa de Falta - CRUD ESPECIFICO
    @PostMapping("/falta")
    public ResponseEntity<String> criarJustificativaFalta(@RequestBody JustificativaDTO dto) {
        justificativaService.criarJustificativaFalta(dto);
        return ResponseEntity.ok("Justificativa de Falta criada com sucesso!");
    }

    @GetMapping("/falta")
    public ResponseEntity<List<JustificativaDTO>> listarJustificativasFalta() {
        return ResponseEntity.ok(justificativaService.listarJustificativasFalta()); // tratar para somente tipo de justificativa = FALTA | FEITO!!
    }

    @GetMapping("/falta/{idJustificativa}")
    public ResponseEntity<Optional<JustificativaDTO>> buscarJustificativaFaltaPorId(@PathVariable Long idJustificativa) {
        return ResponseEntity.ok(justificativaService.buscarJustificativaFaltaPorId(idJustificativa));
    }

    @PutMapping("/falta/{idJustificativa}")
    public ResponseEntity<String> alterarJustificativaFalta(@PathVariable Long idJustificativa, @RequestBody JustificativaDTO dto) {
        if(justificativaService.alterarJustificativaFalta(idJustificativa, dto)) {
            return ResponseEntity.ok("Justificativa de Falta alterada com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/falta/{idJustificativa}")
    public ResponseEntity<String> deletarJustificativaFalta(@PathVariable Long idJustificativa) {
        if (justificativaService.deletarJustificativaFalta(idJustificativa)) {
            return ResponseEntity.ok("Justificativa de Falta deletada do sistema!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Processo de Justificativa de Atraso - CRUD ESPECIFICO
    @PostMapping("/atraso")
    public ResponseEntity<String> criarJustificativaAtraso(@RequestBody JustificativaDTO dto) {
        justificativaService.criarJustificativaAtraso(dto, dto.ocorrenciaId()); // passando o dto, e o id da ocorrencia para associação
        return ResponseEntity.ok("Justificativa de Atraso criada com sucesso!");
    }

    @GetMapping("/atraso")
    public ResponseEntity<List<JustificativaDTO>> listarJustificativasAtraso() {
        return ResponseEntity.ok(justificativaService.listarJustificativasAtraso());
    }

    @GetMapping("/atraso/{idJustificativa}")
    public ResponseEntity<Optional<JustificativaDTO>> buscarJustificativaAtrasoPorId(@PathVariable Long idJustificativa) {
        return ResponseEntity.ok(justificativaService.buscarJustificativaAtrasoPorId(idJustificativa));
    }

    @PutMapping("/atraso/{idJustificativa}")
    public ResponseEntity<String> alterarJustificativaAtraso(@PathVariable Long idJustificativa, @RequestBody JustificativaDTO dto) {
        if(justificativaService.alterarJustificativaAtraso(idJustificativa, dto)) {
            return ResponseEntity.ok("Justificativa de Atraso alterada com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/atraso/{idJustificativa}")
    public ResponseEntity<String> deletarJustificativaAtraso(@PathVariable Long idJustificativa) {
        if(justificativaService.deletarJustificativaAtraso(idJustificativa)) {
            return ResponseEntity.ok("Justificativa de Atraso deletada do sistema!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Processo de Justificativa de Saída Antecipada
    @PostMapping("/saida")
    public ResponseEntity<String> criarJustificativaSaida(@RequestBody JustificativaDTO dto) {
        justificativaService.criarJustificativaSaida(dto, dto.ocorrenciaId());
        return ResponseEntity.ok("Justificativa de Saída Antecipada criada com sucesso!");
    }

    @GetMapping("/saida")
    public ResponseEntity<List<JustificativaDTO>> listarJustificativasSaida() {
        return ResponseEntity.ok(justificativaService.listarJustificativasSaida());
    }

    @GetMapping("/saida/{idJustificativa}")
    public ResponseEntity<Optional<JustificativaDTO>> buscarJustificativaSaidaPorId(@PathVariable Long idJustificativa) {
        return ResponseEntity.ok(justificativaService.buscarJustificativaSaidaPorId(idJustificativa));
    }

    @PutMapping("/saida/{idJustificativa}")
    public ResponseEntity<String> alterarJustificativaSaida(@PathVariable Long idJustificativa, @RequestBody JustificativaDTO dto) {
        if(justificativaService.alterarJustificativaSaida(idJustificativa, dto)) {
            return ResponseEntity.ok("Justificativa de Saída Antecipada alterada com sucesso!");
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/saida/{idJustificativa}")
    public ResponseEntity<String> deletarJustificativaSaida(@PathVariable Long idJustificativa) {
        if(justificativaService.deletarJustificativaSaida(idJustificativa)) {
            return ResponseEntity.ok("Justificativa de Saída Antecipada deletada do sistema!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}