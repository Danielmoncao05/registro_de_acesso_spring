package com.senai.registro_de_acesso_spring.interface_ui.controller;

import com.senai.registro_de_acesso_spring.application.dto.CoordenadorDTO;
import com.senai.registro_de_acesso_spring.application.services.CoordenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coordenadores")
public class CoordenadorControlller {
    @Autowired
    private CoordenadorService coordenadorService;

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody CoordenadorDTO coordenadorDTO) {
        coordenadorService.cadastrar(coordenadorDTO);
        return ResponseEntity.ok("Coordenador '" + coordenadorDTO.nome() + "' cadastrado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<CoordenadorDTO>> listarCoordenadores() {
        return ResponseEntity.ok(coordenadorService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoordenadorDTO> buscarPorId(@PathVariable Long id) {
        return coordenadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody CoordenadorDTO coordenadorDTO) {
        if(coordenadorService.atualizar(id, coordenadorDTO)) {
            return ResponseEntity.ok("Coordenador '" + coordenadorDTO.nome() + "' atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> desativar(@PathVariable Long id) {
        if(coordenadorService.desativar(id)) {
            return ResponseEntity.ok("Coordenador desativado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
