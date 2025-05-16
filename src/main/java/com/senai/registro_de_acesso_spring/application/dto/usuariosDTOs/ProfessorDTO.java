package com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeUsuario;

import java.time.LocalDate;

public record ProfessorDTO(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        String telefone,
        TipoDeUsuario tipoDeUsuario
) {
    public static ProfessorDTO toDTO(Professor p) {
        TipoDeUsuario tipo = TipoDeUsuario.PROFESSOR;
        return new ProfessorDTO(p.getId(), p.getNome(), p.getCpf(), p.getDataNascimento(), p.getEmail(), p.getTelefone(), tipo);
    }

    public Professor fromDTO() {
        Professor professor = new Professor();

        professor.setId(id);
        professor.setNome(nome);
        professor.setCpf(cpf);
        professor.setDataNascimento(dataNascimento);
        professor.setEmail(email);
        professor.setTelefone(telefone);
        professor.setAtivo(true);
        professor.setIdAcesso("");
        professor.setSenha("");
        professor.setFoto("");

        return professor;
    }
}