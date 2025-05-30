package com.senai.registro_de_acesso_spring.interface_ui.controller.turmaControllers.horario;

import com.senai.registro_de_acesso_spring.application.dto.turmaDTOs.horariosDTOs.HorarioPadraoDTO;
import com.senai.registro_de_acesso_spring.application.service.turmaServices.horarioServices.HorarioPadraoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horario-padrao")
public class HorarioPadraoController {

    @Autowired
    private HorarioPadraoService horarioPadraoService;

    @PostMapping("/{semestreId}")
    public ResponseEntity<Void> cadastrarHorario(@PathVariable Long semestreId, @RequestBody HorarioPadraoDTO dto) {
        horarioPadraoService.salvarHorarioPadrao(semestreId, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<HorarioPadraoDTO>> listarHorarios() {
        return ResponseEntity.ok(horarioPadraoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioPadraoDTO> buscarHorarioPorId(@PathVariable Long id) {
        return horarioPadraoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarHorario(@PathVariable Long id, @RequestBody HorarioPadraoDTO dto) {
        if (horarioPadraoService.atualizar(id, dto)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarHorario(@PathVariable Long id) {
        if (horarioPadraoService.deletar(id)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
