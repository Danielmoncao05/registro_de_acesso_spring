package com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.CoordenadorDTO;
import com.senai.registro_de_acesso_spring.application.service.usuariosServices.CoordenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coordenador")
public class CoordenadorController {
    @Autowired
    private CoordenadorService coordenadorService;

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

}