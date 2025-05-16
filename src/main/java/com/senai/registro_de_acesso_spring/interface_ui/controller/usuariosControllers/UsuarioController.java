package com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.UsuarioDTO;
import com.senai.registro_de_acesso_spring.application.services.usuariosServices.UsuarioService;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<String> cadastrarUsuario(@RequestBody UsuarioDTO dto) {
        usuarioService.cadastrarUsuario(dto);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>>listarUsuariosAtivos() {
        return ResponseEntity.ok(usuarioService.listarUsuariosAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarUsuarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        if(usuarioService.atualizarUsuario(id, dto)) {
            return ResponseEntity.ok("Usuário atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> inativarUsuario(@PathVariable Long id) {
        if(usuarioService.inativarUsuario(id)) {
            return ResponseEntity.ok("Usuário desativado do sistema!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}