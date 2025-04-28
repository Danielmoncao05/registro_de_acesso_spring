package com.senai.registro_de_acesso_spring.presentation.controllers;

import com.senai.registro_de_acesso_spring.domain.entities_BAGUNCADO.Usuario;
import com.senai.registro_de_acesso_spring.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
        // mover por service
    @Autowired
    private UsuarioRepository userRepository;

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

    @PostMapping
    public Usuario createUser(@RequestBody Usuario user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @RequestBody Usuario userUpdated) {
        Optional<Usuario> userExistents = userRepository.findById(id);

        if(userExistents.isPresent()) {
            Usuario user = userExistents.get();

            user.setIdDeAcesso(userUpdated.getIdDeAcesso());
            user.setNome(userUpdated.getNome());
            user.setTelefone(userUpdated.getTelefone());
            user.setEmail(userUpdated.getEmail());
            user.setFoto(userUpdated.getFoto());

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
    }

}