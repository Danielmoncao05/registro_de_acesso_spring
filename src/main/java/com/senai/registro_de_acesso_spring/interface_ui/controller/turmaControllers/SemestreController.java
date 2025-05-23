package com.senai.registro_de_acesso_spring.interface_ui.controller.turmaControllers;

import com.senai.registro_de_acesso_spring.application.dto.turmaDTOs.SemestreDTO;
import com.senai.registro_de_acesso_spring.application.service.turmaServices.SemestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subturmas/{subTurmaId}/semestres")
public class SemestreController {
    @Autowired
    private SemestreService semestreService;

    @PostMapping
    public ResponseEntity<Void> criarSemestre(@PathVariable Long subTurmaId) {
        semestreService.criarSemestre(subTurmaId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<SemestreDTO>> listarSemestres() {
        return ResponseEntity.ok(semestreService.listarSemestres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SemestreDTO> buscarSemestrePorId(@PathVariable Long id) {
        return semestreService.buscarSemestrePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarSemestre(@PathVariable Long id, @RequestBody SemestreDTO dto) {
        if (semestreService.atualizarSemestre(id, dto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSemestre(@PathVariable Long id) {
        if (semestreService.deletarSemestre(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
