package com.senai.registro_de_acesso_spring.presentation.controllers;


import com.senai.registro_de_acesso_spring.application.dto.AQVDto;
import com.senai.registro_de_acesso_spring.domain.service.AQVService;
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
    public ResponseEntity<Void> cadastrarAqv (@RequestBody AQVDto aqvDto) {
        aqvService.cadastrarAQV(aqvDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AQVDto> buscarPorId(@PathVariable Long id) {
        return aqvService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AQVDto>> listar() {
        return ResponseEntity.ok(aqvService.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarAqv(@PathVariable Long id, @RequestBody AQVDto aqvDto) {

        if (aqvService.atualizar(id, aqvDto)) {
            return ResponseEntity.ok("AQV atualizado!!!!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarAqv(@PathVariable Long id) {

        if (aqvService.deletarAqv(id)) {
            return ResponseEntity.ok("AQV deletado!!!!!");
        } else  {
            return ResponseEntity.notFound().build();
        }
    }
}
