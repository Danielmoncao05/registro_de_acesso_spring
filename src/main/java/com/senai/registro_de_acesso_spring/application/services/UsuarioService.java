package com.senai.registro_de_acesso_spring.application.services;

import com.senai.registro_de_acesso_spring.application.dto.UsuarioDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.AQV;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Coordenador;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Usuario;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeUsuario;
import com.senai.registro_de_acesso_spring.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void cadastrarUsuario(UsuarioDTO dto) {
        Usuario usuario;

        switch(dto.tipoDeUsuario()) {
            case TipoDeUsuario.ALUNO -> usuario = new Aluno();
            case TipoDeUsuario.AQV -> usuario = new AQV();
            case TipoDeUsuario.COORDENADOR -> usuario = new Coordenador();
            case TipoDeUsuario.PROFESSOR -> usuario = new Professor();
            default -> throw new IllegalArgumentException("Tipo de usuário inválido");
        }
        
        usuario.setNome(dto.nome());
        usuario.setCpf(dto.cpf());
        usuario.setEmail(dto.email());
        usuario.setDataNascimento(dto.dataNascimento());
        usuario.setIdAcesso("");
        usuario.setSenha("");

        usuarioRepository.save(usuario);
    }

    public List<UsuarioDTO> listarUsuarios(){
        return usuarioRepository.findAll().stream().map(
                us -> new UsuarioDTO(us.getId(),
                        us.getNome(),
                        us.getCpf(),
                        us.getDataNascimento(),
                        us.getEmail(),us.getTipoDeUsuario())

        ).toList();
    }
}