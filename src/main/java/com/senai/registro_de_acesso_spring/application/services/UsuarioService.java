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
        Usuario tipoUsuario;

        switch(dto.tipoDeUsuario()) {
            case TipoDeUsuario.ALUNO -> tipoUsuario = new Aluno();
            case TipoDeUsuario.AQV -> tipoUsuario = new AQV();
            case TipoDeUsuario.COORDENADOR -> tipoUsuario = new Coordenador();
            case TipoDeUsuario.PROFESSOR -> tipoUsuario = new Professor();
            default -> throw new IllegalArgumentException("Tipo de usuário inválido");
        }
        
        tipoUsuario.setNome(dto.nome());
        tipoUsuario.setCpf(dto.cpf());
        tipoUsuario.setEmail(dto.email());
        tipoUsuario.setDataNascimento(dto.dataNascimento());
        tipoUsuario.setIdAcesso("");
        tipoUsuario.setSenha("");

        usuarioRepository.save(tipoUsuario);
    }

    public List<UsuarioDTO> listarUsuarios(UsuarioDTO dto) {
        Usuario tipoUsuario;

        switch(dto.tipoDeUsuario()) {
            case TipoDeUsuario.ALUNO -> tipoUsuario = new Aluno();
            case TipoDeUsuario.AQV -> tipoUsuario = new AQV();
            case TipoDeUsuario.COORDENADOR -> tipoUsuario = new Coordenador();
            case TipoDeUsuario.PROFESSOR -> tipoUsuario = new Professor();
            default -> throw new IllegalArgumentException("Tipo de usuário inválido");
        }

        return usuarioRepository.findAll().stream().map( usuario -> new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getDataNascimento(),
                usuario.getEmail(),
                dto.tipoDeUsuario()
                )).toList();
    }


}