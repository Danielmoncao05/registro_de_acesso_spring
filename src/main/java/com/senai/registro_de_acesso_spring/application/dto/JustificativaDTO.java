package com.senai.registro_de_acesso_spring.application.dto;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.registro_de_acesso_spring.domain.enuns.StatusDaJustificativa;
import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeJustifcativa;

import java.time.LocalDate;

public record JustificativaDTO(
        Long id,
        TipoDeJustifcativa tipo,
        String descricao,
        String anexo,
        LocalDate dataInicial,
        Integer quantidadeDias,
        StatusDaJustificativa status) {

    public static JustificativaDTO toDTO(Justificativa j) {

        return new JustificativaDTO(
                j.getId(),
                j.getTipo(),
                j.getDescricao(),
                j.getAnexo(),
                j.getDataInicial(),
                j.getQuantidadeDias(),
                j.getStatus()
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
        justificativa.setStatus(status);
        return justificativa;
    }
}
