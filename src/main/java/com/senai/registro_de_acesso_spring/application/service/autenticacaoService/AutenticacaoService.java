package com.senai.registro_de_acesso_spring.application.service.autenticacaoService;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Coordenador;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeUsuario;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.AQV;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Usuario;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder codificadorSenha;

    public Usuario autenticar(String username, String senha) {
        return usuarioRepository.findByUsername(username)
                .filter(user -> codificadorSenha.matches(senha, user.getSenha()))
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
    }

    public Usuario registrar(String nome, String username, String senha, TipoDeUsuario tipoDeUsuario ) {
        Usuario usuario;

        switch (tipoDeUsuario) {
            case ALUNO -> usuario = new Aluno();
            case PROFESSOR -> usuario = new Professor();
            case AQV -> usuario = new AQV();
            case COORDENADOR -> usuario = new Coordenador();
            default -> throw new IllegalArgumentException("Tipo de Usuário inválido");
        }

        usuario.setNome(nome);
        usuario.setUsername(username);
        usuario.setSenha(codificadorSenha.encode(senha));
        usuario.setTipoDeUsuario(tipoDeUsuario);

        return usuarioRepository.save(usuario);
    }
}


