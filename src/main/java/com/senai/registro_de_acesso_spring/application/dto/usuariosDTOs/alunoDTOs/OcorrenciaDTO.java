package com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.registro_de_acesso_spring.domain.enums.StatusDaOcorrencia;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeOcorrencia;

import java.time.LocalDateTime;

public record OcorrenciaDTO(
        Long id,
        TipoDeOcorrencia tipo,
        String descricao,
        StatusDaOcorrencia status,
        LocalDateTime dataHoraCriacao,
        LocalDateTime dataHoraConclusao,
        Long alunoId,
        Long professorResponsavelId
) {
    public static OcorrenciaDTO toDTO(Ocorrencia o) {
        return new OcorrenciaDTO(
                o.getId(),
                o.getTipo(),
                o.getDescricao(),
                o.getStatus(),
                o.getDataHoraCriacao(),
                o.getDataHoraConclusao(),
                o.getAluno() != null ? o.getAluno().getId() : null,
                o.getProfessorResponsavel() != null ? o.getProfessorResponsavel().getId() : null
        );
    }

    public Ocorrencia fromDTO() { // cria um dto
        Ocorrencia ocorrencia = new Ocorrencia();

        ocorrencia.setId(id);
        ocorrencia.setTipo(tipo);
        ocorrencia.setDescricao(descricao);
        ocorrencia.setStatus(status);
        ocorrencia.setDataHoraCriacao(dataHoraCriacao);
        ocorrencia.setDataHoraConclusao(dataHoraConclusao);

        return ocorrencia;
    }
}