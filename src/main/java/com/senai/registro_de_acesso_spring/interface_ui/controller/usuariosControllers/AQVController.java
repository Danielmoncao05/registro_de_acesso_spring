package com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.AQVDTO;
import com.senai.registro_de_acesso_spring.application.services.usuariosServices.AQVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aqv")
public class AQVController {
    @Autowired
    AQVService aqvService;

    @PostMapping
    public ResponseEntity<Void> cadastrarAQV(@RequestBody AQVDTO dto) {
        aqvService.cadastrarAQV(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<AQVDTO>> listarAQVs() {
        return ResponseEntity.ok(aqvService.listarAQVs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AQVDTO> buscarAQVPorId(@PathVariable Long id) {
        return aqvService.buscarAQVPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarAQV(@PathVariable Long id, @RequestBody AQVDTO dto) {
        if(aqvService.atualizarAQV(id, dto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativarAQV(@PathVariable Long id) {
        if(aqvService.inativarAQV(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}