package com.senai.registro_de_acesso_spring.apresentation.Controller;


import com.senai.registro_de_acesso_spring.domain.entity.Usuario;
import com.senai.registro_de_acesso_spring.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/usuarios")
public class UsuarioController {

@Autowired
    private UsuarioRepository usuarioRepository;

@GetMapping
public List<Usuario> getUsuarios () {
    return usuarioRepository.findAll();
}

@PostMapping
    public Usuario createUsuario (@RequestBody Usuario usuario) {
    return usuarioRepository.save(usuario);
}

@GetMapping ("/{id}")
    public ResponseEntity<Usuario> getUsuarioById (@PathVariable Long id) {
    Optional<Usuario> usuario = usuarioRepository.findById(id);
    return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
}

@PutMapping ("/{id}")
    public ResponseEntity<Usuario> updateUsuario (@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {

    Optional<Usuario> usuarioExistents = usuarioRepository.findById(id);

    if (usuarioExistents.isPresent()) {
        Usuario usuario = usuarioExistents.get();

        usuario.setIdAcesso(usuarioAtualizado.getIdAcesso());
        usuario.setCPF(usuarioAtualizado.getCPF());
        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setSenha(usuarioAtualizado.getSenha());
        usuario.setTelefone(usuarioAtualizado.getTelefone());

        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario); //
    } else  {
        return ResponseEntity.notFound().build();
    }
}

@DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteUsuario ( @PathVariable Long id) {

if (usuarioRepository.existsById(id)) {
    usuarioRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT); //
}else  {
    return ResponseEntity.notFound().build();
}
}



}

