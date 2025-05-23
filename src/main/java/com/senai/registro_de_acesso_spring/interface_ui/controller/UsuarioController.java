package com.senai.registro_de_acesso_spring.interface_ui.controller;

import com.senai.registro_de_acesso_spring.application.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
        // mover por service
    @Autowired
    UsuarioService usuarioService;
/*
    @GetMapping
    public List<Usuario> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        Optional<Usuario> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
*/
    @PostMapping
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody UsuarioDTO dto) {
        usuarioService.cadastrarUsuario(dto);
        return ResponseEntity.ok().build();
    }
/*
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @RequestBody Usuario userUpdated) {
        Optional<Usuario> userExistents = userRepository.findById(id);

        if(userExistents.isPresent()) {
            Usuario user = userExistents.get();

            user.setIdDeAcesso(userUpdated.getIdDeAcesso());
            user.setNome(userUpdated.getNome());
            user.setTelefone(userUpdated.getTelefone());
            user.setEmail(userUpdated.getEmail());
            user.s

            userRepository.save(user);

            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);

            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

}