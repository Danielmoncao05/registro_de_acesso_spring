package com.senai.registro_de_acesso_spring.presentation.controllers;


import com.senai.registro_de_acesso_spring.application.dto.AmbienteDTO;
import com.senai.registro_de_acesso_spring.application.services.AmbienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ambientes")
public class AmbienteController {

    @Autowired
    private AmbienteService ambienteService;

    @PostMapping
    public ResponseEntity<String> adicionarAmbiente(@RequestBody AmbienteDTO ambienteDTO){
        ambienteService.adicionarAmbiente(ambienteDTO);
        return ResponseEntity.ok("Ambiente adicionado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<AmbienteDTO>> listarAmbientes(){
        return ResponseEntity.ok(ambienteService.listarAmbientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AmbienteDTO> buscarPorId(@PathVariable Long id){
        return ambienteService.buscarAmbientePorId(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarPorId(@PathVariable Long id,@RequestBody AmbienteDTO ambienteDTO){
        if (ambienteService.atualizarAmbientePorId(id,ambienteDTO)){
            return ResponseEntity.ok("Ambiente atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarAmbiente(@PathVariable Long id){
        if (ambienteService.deletarAmbiente(id)){
            return ResponseEntity.ok("Ambiente deletado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
