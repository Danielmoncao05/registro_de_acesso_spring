package com.senai.registro_de_acesso_spring.application.dto.turmaDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.turma.SubTurma;

public record SubTurmaDTO(
        Long id,
        String nome,
        Long turmaId
) {
    public static SubTurmaDTO toDTO(SubTurma sbt) {
        return new SubTurmaDTO(
                sbt.getId(),
                sbt.getNome(),
                sbt.getTurma().getId()
        );
    }

    public SubTurma fromDTO() {
        SubTurma subTurma = new SubTurma();

        subTurma.setNome(nome);

        return subTurma;
    }
}