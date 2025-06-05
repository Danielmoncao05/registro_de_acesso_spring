package com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.registro_de_acesso_spring.domain.enums.StatusDaJustificativa;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeJustifcativa;

import java.time.LocalDate;

public record JustificativaDTO(
        Long id,
        TipoDeJustifcativa tipo,
        String descricao,
        String anexo,
        LocalDate dataInicial,
        Integer quantidadeDias,
        StatusDaJustificativa status,
        Aluno aluno,
        Ocorrencia ocorrencia
) {
    public static JustificativaDTO toDTO(Justificativa j) {
        return new JustificativaDTO(
                j.getId(),
                j.getTipo(),
                j.getDescricao(),
                j.getAnexo(),
                j.getDataInicial(),
                j.getQuantidadeDias(),
                j.getStatus(),
                j.getAluno(),
                j.getOcorrencia()
        );
    }

    public Justificativa fromDTO() {
        Justificativa justificativa = new Justificativa();

        justificativa.setId(id);
        justificativa.setTipo(tipo);
        justificativa.setDescricao(descricao);
        justificativa.setAnexo(anexo);
        justificativa.setDataInicial(dataInicial);
        justificativa.setQuantidadeDias(quantidadeDias);
        justificativa.setAluno(aluno);
        justificativa.setOcorrencia(ocorrencia);

        return justificativa;
    }
}