package com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.CoordenadorDTO;
import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.JustificativaDTO;
import com.senai.registro_de_acesso_spring.application.service.usuariosServices.CoordenadorService;
import com.senai.registro_de_acesso_spring.application.service.usuariosServices.alunoServices.JustificativaService;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeJustificativa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coordenador")
public class CoordenadorController {
    @Autowired
    private CoordenadorService coordenadorService;

    @Autowired
    private JustificativaService justificativaService;

    // CRUD de Coordenador
    @PostMapping
    public ResponseEntity<String> cadastrarCoordenador(@RequestBody CoordenadorDTO dto) {
        coordenadorService.cadastrarCoordenador(dto);
        return ResponseEntity.ok("Coordenador(a) '" + dto.nome() + "' cadastrado(a) com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<CoordenadorDTO>> listarCoordenadores() {
        return ResponseEntity.ok(coordenadorService.listarCoordenadores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoordenadorDTO> buscarCoordenadorPorId(@PathVariable Long id) {
        return coordenadorService.buscarCoordenadorPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarCoordenador(@PathVariable Long id, @RequestBody CoordenadorDTO dto) {
        if(coordenadorService.atualizarCoordenador(id, dto)) {
            return  ResponseEntity.ok("Coordenador(a) '" +  dto.nome() + "' atualizado(a) com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> inativarCoordenador(@PathVariable Long id) {
        if(coordenadorService.inativarCoordenador(id)) {
            return ResponseEntity.ok("Coordenador(a) desativado(a) do sistema!");
        }
        return ResponseEntity.notFound().build();
    }

    // Coordenador visualizar Justificativas de Faltas
    @GetMapping("/falta")
    public ResponseEntity<List<JustificativaDTO>> listarJustificativasFalta() { return ResponseEntity.ok(justificativaService.listarJustificativasFalta()); }

    // Coordenador alterar Status de Justificativa de Falta
    @PutMapping("/falta/{idJustificativa}")
    public ResponseEntity<String> alterarStatusJustificativaFalta(@PathVariable Long idJustificativa, @RequestBody JustificativaDTO dto) { // payload é o json com os dados
        if(coordenadorService.alterarStatusJustificativas(idJustificativa, dto, TipoDeJustificativa.FALTA).isPresent()) {
            return ResponseEntity.ok("Status da Justificativa de Falta alterado para '" + dto.status() + "' com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Coordenador visualizar Justificativas de Atraso
    @GetMapping("/atraso")
    public ResponseEntity<List<JustificativaDTO>> listarJustificativasAtraso() { return ResponseEntity.ok(justificativaService.listarJustificativasAtraso()); }

    // Coordenador alterar Status de Justificativa de Atraso
    @PutMapping("/atraso/{idJustificativa}")
    public ResponseEntity<String> alterarStatusJustificativaAtraso(@PathVariable Long idJustificativa, @RequestBody JustificativaDTO dto) {
        if(coordenadorService.alterarStatusJustificativas(idJustificativa, dto, TipoDeJustificativa.ATRASO).isPresent()) {
            return ResponseEntity.ok("Status da Justificativa de Atraso alterado para '" + dto.status() + "' com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Coordenador visualizar Justificativas de Saída Antecipada
    @GetMapping("/saida")
    public ResponseEntity<List<JustificativaDTO>> listarJustificativasSaida() { return ResponseEntity.ok(justificativaService.listarJustificativasSaida()); }

    // Coordenador alterar Status de Justificativa de Saída Antecipada
    @PutMapping("/saida/{idJustificativa}")
    public ResponseEntity<String> alterarStatusJustificativaSaida(@PathVariable Long idJustificativa, @RequestBody JustificativaDTO dto) {
        if(coordenadorService.alterarStatusJustificativas(idJustificativa, dto, TipoDeJustificativa.SAIDA_ANTECIPADA).isPresent()) {
            return ResponseEntity.ok("Status da Justificativa de Saída Antecipada alterado para '" + dto.status() + "' com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}