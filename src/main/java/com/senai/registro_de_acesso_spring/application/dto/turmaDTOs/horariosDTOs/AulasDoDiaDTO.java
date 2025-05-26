package com.senai.registro_de_acesso_spring.application.dto.turmaDTOs.horariosDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.AulasDoDia;
import com.senai.registro_de_acesso_spring.domain.enums.DiasDaSemana;

import java.util.List;

public record AulasDoDiaDTO(
        Long id,
        DiasDaSemana diaDaSemana,
        List<AulaDTO> aulas
) {
    public static AulasDoDiaDTO toDTO(AulasDoDia ad) {
        return new AulasDoDiaDTO(
                ad.getId(),
                ad.getDiaDaSemana(),
                ad.getAulas().stream().map(AulaDTO::toDTO).toList()
        );
    }

    public AulasDoDia fromDTO() {
        AulasDoDia dia = new AulasDoDia();

        dia.setDiaDaSemana(diaDaSemana);
        dia.setAulas(aulas.stream().map(AulaDTO::fromDTO).toList());

        return dia;
    }
}