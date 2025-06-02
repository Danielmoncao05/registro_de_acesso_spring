package com.senai.registro_de_acesso_spring.interface_ui.controller.autenticacaoController;

import com.senai.registro_de_acesso_spring.application.service.autenticacaoService.AutenticacaoService;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Usuario;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeUsuario;
import com.senai.registro_de_acesso_spring.infrastructure.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {
    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("senha");

        Usuario user = autenticacaoService.autenticar(username, password);

        String token = jwtUtil.gerarToken(user.getId(), user.getUsername(), String.valueOf(user.getTipoDeUsuario()));

        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> register(@RequestBody Map<String, String> payload) {
        String nome = payload.get("nome");
        String username = payload.get("username");
        String password = payload.get("senha");

        TipoDeUsuario tipoDeUsuario = TipoDeUsuario.valueOf(payload.get("tipoDeUsuario"));

        Usuario user = autenticacaoService.registrar(nome, username, password, tipoDeUsuario);

        return ResponseEntity.ok(Map.of("message", "Usuário cadastrado com sucesso", "id", user.getId()));
    }
}