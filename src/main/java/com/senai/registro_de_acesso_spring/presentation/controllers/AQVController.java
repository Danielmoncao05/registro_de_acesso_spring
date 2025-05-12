package com.senai.registro_de_acesso_spring.presentation.controllers;


import com.senai.registro_de_acesso_spring.application.dto.AQVDto;
import com.senai.registro_de_acesso_spring.domain.service.AQVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    }
}
