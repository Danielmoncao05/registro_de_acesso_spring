package com.senai.registro_de_acesso_spring.application.dto;

import com.senai.registro_de_acesso_spring.domain.entity.turma.SubTurma;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeUsuario;

import java.time.LocalDate;
import java.util.List;

public record AlunoDTO(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        String telefone
        ) {
}
