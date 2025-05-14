package com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.turma.SubTurma;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeUsuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record AlunoDTO(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        TipoDeUsuario tipo,
        List<Ocorrencia> ocorrencias,
        List<Justificativa> justificativas,
        List<SubTurma> subTurmas
) {
    public static AlunoDTO toDTO(Aluno a) {
        TipoDeUsuario tipo = TipoDeUsuario.ALUNO;
        // criação das List's vazias
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        List<Justificativa> justificativas = new ArrayList<>();
        List<SubTurma> subTurmas = new ArrayList<>();

        return new AlunoDTO(a.getId(), a.getNome(), a.getCpf(), a.getDataNascimento(), a.getEmail(), tipo, ocorrencias, justificativas, subTurmas);
    }

    public Aluno fromDTO() {
        Aluno aluno = new Aluno();

        aluno.setId(id);
        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setDataNascimento(dataNascimento);
        aluno.setAtivo(true);
        aluno.setIdAcesso("");
        aluno.setSenha("");
        aluno.setSubTurmas(subTurmas);
        aluno.setJustificativas(justificativas);
        aluno.setOcorrencias(ocorrencias);

        return aluno;
    }
}