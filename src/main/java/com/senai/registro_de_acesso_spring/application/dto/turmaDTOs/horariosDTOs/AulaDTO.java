package com.senai.registro_de_acesso_spring.application.dto.turmaDTOs.horariosDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.Aula;

public record AulaDTO(
        Long id,
        Integer ordem,
        Long unidadeCurricularId,
        Long professorId,
        Long ambienteId
) {
    public static AulaDTO toDTO(Aula a) {
        return new AulaDTO(
                a.getId(),
                a.getOrdem(),
                a.getUnidadeCurricular() != null ? a.getUnidadeCurricular().getId() : null,
                a.getProfessor() != null ? a.getProfessor().getId() : null,
                a.getAmbiente() != null ? a.getAmbiente().getId() : null
        );
    }

    public Aula fromDTO() {
        Aula aula = new Aula();
        return aula;
    }

}