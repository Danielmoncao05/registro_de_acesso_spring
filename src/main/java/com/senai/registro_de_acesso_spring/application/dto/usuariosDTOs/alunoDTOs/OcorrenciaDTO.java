package com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.curso.UnidadeCurricular;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.registro_de_acesso_spring.domain.enuns.StatusDaOcorrencia;
import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeOcorrencia;

import java.time.LocalDateTime;

public record OcorrenciaDTO(
        Long id,
        TipoDeOcorrencia tipo,
        String descricao,
        StatusDaOcorrencia status,
        LocalDateTime dataHoraCriacao,
        LocalDateTime dataHoraConclusao,
        Aluno aluno,
        Professor professor,
        UnidadeCurricular unidadeCurricular
) {
    public static OcorrenciaDTO toDTO(Ocorrencia o) { // pega os atributos
        return new OcorrenciaDTO(o.getId(), o.getTipo(), o.getDescricao(), o.getStatus(), o.getDataHoraCriacao(), o.getDataHoraConclusao(), o.getAluno(), o.getProfessorResponsavel(), o.getUnidadeCurricular());
    }

    public Ocorrencia fromDTO() { // cria um dto
        Ocorrencia ocorrencia = new Ocorrencia();

        ocorrencia.setId(id);
        ocorrencia.setTipo(tipo);
        ocorrencia.setDescricao(descricao);
        ocorrencia.setStatus(status);
        ocorrencia.setDataHoraCriacao(dataHoraCriacao);
        ocorrencia.setDataHoraConclusao(dataHoraConclusao);
        ocorrencia.setAluno(aluno);
        ocorrencia.setProfessorResponsavel(professor);
        ocorrencia.setUnidadeCurricular(unidadeCurricular);
        ocorrencia.setAtivo(true);

        return ocorrencia;
    }
}