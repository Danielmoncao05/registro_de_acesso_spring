package com.senai.registro_de_acesso_spring.application.dto.turmaDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.turma.Semestre;

public record SemestreDTO(
        Long id,
        int numero,
        String nomeDaTurma
) {
    public static SemestreDTO toDTO(Semestre s) {
        return new SemestreDTO(
                s.getId(),
                s.getNumero(),
                s.getNomeDaTurma()
        );
    }
}