package com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.CoordenadorDTO;
import com.senai.registro_de_acesso_spring.application.service.usuariosServices.CoordenadorService;
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
    public ResponseEntity<String> cadastrarCoordenador(@RequestBody CoordenadorDTO coordenadorDTO) {
        coordenadorService.cadastrarCoordenador(coordenadorDTO);
        return ResponseEntity.ok(coordenadorDTO.nome() + " cadastrado com sucesso!");
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
    public ResponseEntity<String> atualizarCoordenador(@PathVariable Long id, @RequestBody CoordenadorDTO coordenadorDTO) {
        if(coordenadorService.atualizarCoordenador(id, coordenadorDTO)) {
            return ResponseEntity.ok(coordenadorDTO.nome() + " atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> inativarCoordenador(@PathVariable Long id) {
        if(coordenadorService.inativarCoordenador(id)) {
            return ResponseEntity.ok("Coordenador desativado do sistema!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
