package com.senai.registro_de_acesso_spring.application.dto.cursoDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.curso.Curso;
import com.senai.registro_de_acesso_spring.domain.entity.curso.UnidadeCurricular;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record UnidadeCurricularDTO(
        Long id,
        String nome,
        Integer cargaHorariaTotal,
        Map<Integer, Integer> cargaHorariaPorSemestre,
        Curso curso,
        List<Professor> professor
) {
    public static UnidadeCurricularDTO toDTO(UnidadeCurricular uc) {
        List<Professor> professor = new ArrayList<>();
        return new UnidadeCurricularDTO(uc.getId(), uc.getNome(), uc.getCargaHorariaTotal(), uc.getCargaHorariaPorSemestre(), uc.getCurso(), professor);
    }

    public UnidadeCurricular fromDTO() {
        UnidadeCurricular unidadeCurricular = new UnidadeCurricular();

        unidadeCurricular.setId(id);
        unidadeCurricular.setNome(nome);
        unidadeCurricular.setCargaHorariaTotal(cargaHorariaTotal);
        unidadeCurricular.setCargaHorariaPorSemestre(cargaHorariaPorSemestre);
        unidadeCurricular.setCurso(curso);
        unidadeCurricular.setProfessor(professor);

        return unidadeCurricular;
    }
}