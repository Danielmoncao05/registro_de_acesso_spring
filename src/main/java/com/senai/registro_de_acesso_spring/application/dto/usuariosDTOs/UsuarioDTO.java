package com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.AQV;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Coordenador;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Usuario;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeUsuario;

import java.time.LocalDate;

public record UsuarioDTO(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String idAcesso,
        String email,
        String telefone,
        String username,
        TipoDeUsuario tipoDeUsuario
)
{
    public static  UsuarioDTO toDTO(Usuario u) {
        TipoDeUsuario tipo = switch(u) {
            case Aluno aluno -> TipoDeUsuario.ALUNO;
            case Professor professor -> TipoDeUsuario.PROFESSOR;
            case Coordenador coordenador -> TipoDeUsuario.COORDENADOR;
            case AQV aqv -> TipoDeUsuario.AQV;
            default -> throw new IllegalArgumentException("Tipo de usuário desconhecido");
        };
        return new UsuarioDTO(u.getId(), u.getNome(), u.getCpf(), u.getDataNascimento(), u.getIdAcesso(), u.getEmail(), u.getTelefone(), u.getUsername(),tipo);
    }

    public Usuario fromDTO() {
        Usuario usuario = switch(tipoDeUsuario) {
            case ALUNO -> new Aluno();
            case PROFESSOR -> new Professor();
            case COORDENADOR -> new Coordenador();
            case AQV -> new AQV();
        };

        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setDataNascimento(dataNascimento);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuario.setIdAcesso(idAcesso);
        usuario.setUsername(username);
        usuario.setAtivo(true);

        return usuario;
    }

}