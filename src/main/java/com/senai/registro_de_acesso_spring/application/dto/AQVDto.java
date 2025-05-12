package com.senai.registro_de_acesso_spring.application.dto;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.AQV;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Coordenador;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeUsuario;

import java.time.LocalDate;

public record AQVDto(Long id,
                     String nome,
                     String cpf,
                     LocalDate dataNascimento,
                     String email) {

    public static AQVDto toDTO(AQV a) {

        return new AQVDto(
                a.getId(),
                a.getNome(),
                a.getCpf(),
                a.getDataNascimento(),
                a.getEmail()
        );
    }

    public AQV fromDTO() {
        AQV aqv = new AQV();

        aqv.setId(id);
        aqv.setNome(nome);
        aqv.setCpf(cpf);
        aqv.setDataNascimento(dataNascimento);
        aqv.setEmail(email);
        aqv.setIdAcesso("");
        aqv.setSenha("");
        return aqv;
    }
}
