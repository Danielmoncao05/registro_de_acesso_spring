package com.senai.registro_de_acesso_spring.application.dto.cursoDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.curso.Curso;
import com.senai.registro_de_acesso_spring.domain.entity.curso.UnidadeCurricular;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;

import java.util.List;

public record UnidadeCurricularDTO(
        Long id,
        String nome,
        Integer cargaHorariaTotal,
        Curso curso,
        List<Professor> professoresHabilitados
) {
    public static UnidadeCurricularDTO toDTO(UnidadeCurricular uc) {
        return new UnidadeCurricularDTO(
                uc.getId(),
                uc.getNome(),
                uc.getCargaHorariaTotal(),
                uc.getCurso(),
                uc.getProfessor()
        );
    }

    public UnidadeCurricular fromDTO(Curso curso) {
        UnidadeCurricular uc = new UnidadeCurricular();

        uc.setNome(nome);
        uc.setCargaHorariaTotal(cargaHorariaTotal);
        uc.setCurso(curso);
        uc.setProfessor(professoresHabilitados);

        return uc;
    }
}