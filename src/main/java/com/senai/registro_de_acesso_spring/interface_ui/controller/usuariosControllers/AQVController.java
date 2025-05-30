package com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.AQVDTO;
import com.senai.registro_de_acesso_spring.application.service.usuariosServices.AQVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aqv")
public class AQVController {

    @Autowired
    private AQVService aqvService;

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
}
