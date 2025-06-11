package com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.AQVDTO;
import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.JustificativaDTO;
import com.senai.registro_de_acesso_spring.application.service.usuariosServices.AQVService;
import com.senai.registro_de_acesso_spring.application.service.usuariosServices.alunoServices.JustificativaService;
import com.senai.registro_de_acesso_spring.domain.enums.StatusDaJustificativa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aqv")
public class AQVController {
    @Autowired
    private AQVService aqvService;

    @Autowired
    private JustificativaService justificativaService;

    @PostMapping
    public ResponseEntity<String> cadastrarAQV(@RequestBody AQVDTO dto) {
        aqvService.cadastrarAQV(dto);
        return ResponseEntity.ok("AQV '" + dto.nome() + "' cadastrado(a) com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AQVDTO> buscarPorId(@PathVariable Long id) {
        return aqvService.buscarAQVPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AQVDTO>> listarAQVs() {
        return ResponseEntity.ok(aqvService.listarAQVs());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarAQV(@PathVariable Long id, @RequestBody AQVDTO dto) {
        if (aqvService.atualizarAQV(id, dto)) {
            return ResponseEntity.ok("AQV '" + dto.nome() + "' atualizado(a) com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> inativarAQV(@PathVariable Long id) {
        if (aqvService.inativarAQV(id)) {
            return ResponseEntity.ok("AQV desativado(a) com sucesso!");
        } else  {
            return ResponseEntity.notFound().build();
        }
    }

    // AQV visualizar Justificativas de Faltas
    @GetMapping("/falta")
    public ResponseEntity<List<JustificativaDTO>> listarJustificativasFalta() { return ResponseEntity.ok(justificativaService.listarJustificativasFalta()); }

    // AQV alterar status de Justificativa de Falta
    @PutMapping("/falta/{idJustificativa}")
    public ResponseEntity<String> alterarStatusJustificativaFalta(@PathVariable Long idJustificativa, @RequestBody JustificativaDTO dto) { // payload é o json com os dados
        if(aqvService.alterarStatusJustificativaFalta(idJustificativa, dto).isPresent()) {
            return ResponseEntity.ok("Status da Justificativa de Falta alterado para '" + dto.status() + "' com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // AQV visualizar justificativas de Atraso
    @GetMapping("/atraso")
    public ResponseEntity<List<JustificativaDTO>> listarJustificativasAtraso() { return ResponseEntity.ok(justificativaService.listarJustificativasAtraso()); }

    // AQV alterar Status de Justificativa de Atraso
    @PutMapping("/atraso/{idJustificativa}")
    public ResponseEntity<String> alterarStatusJustificativaAtraso(@PathVariable Long idJustificativa, @RequestBody JustificativaDTO dto) {
        if(aqvService.alterarStatusJustificativaAtraso(idJustificativa, dto).isPresent()) {
            return ResponseEntity.ok("Status da Justificativa de Atraso alterado para '" + dto.status() + "' com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // AQV visualizar Justificativas de Saída Antecipada
    @GetMapping("/saida")
    public ResponseEntity<List<JustificativaDTO>> listarJustificativasSaida() {return ResponseEntity.ok(justificativaService.listarJustificativasSaida()); }

    // AQV alterar Status de Justificativa de Saída Antecipada
    @PutMapping("/saida/{idJustificativa}")
    public ResponseEntity<String> alterarStatusJustificativaSaida(@PathVariable Long idJustificativa, @RequestBody JustificativaDTO dto) {
        if(aqvService.alterarStatusJustificativaSaida(idJustificativa, dto).isPresent()) {
            return ResponseEntity.ok("Status da Justificativa de Saída Antecipada alterado para '" + dto.status() + "' com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}