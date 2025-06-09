package com.senai.registro_de_acesso_spring.application.dto.cursoDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.curso.Curso;
import com.senai.registro_de_acesso_spring.domain.entity.curso.UnidadeCurricular;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeCurso;

import java.util.List;

public record CursoDTO(
        Long id,
        String titulo,
        TipoDeCurso tipo,
        Integer cargaHoraria,
        Integer toleranciaMinutos,
        List<UnidadeCurricularDTO> unidadesCurriculares
) {
    public static CursoDTO toDTO(Curso c) {
        return new CursoDTO(
                c.getId(),
                c.getTitulo(),
                c.getTipo(),
                c.getCargaHoraria(),
                c.getToleranciaMinutos(),
                c.getUnidadesCurriculares()
                        .stream()
                        .map(UnidadeCurricularDTO::toDTO)
                        .toList()
        );
    }

    public Curso fromDTO() {
        Curso curso = new Curso();

        curso.setTitulo(titulo);
        curso.setTipo(tipo);
        curso.setCargaHoraria(cargaHoraria);
        curso.setToleranciaMinutos(toleranciaMinutos);

        List<UnidadeCurricular> ucs = unidadesCurriculares
                .stream()
                .map(dto -> dto.fromDTO(curso))
                .toList()
        ;

        curso.setUnidadesCurriculares(ucs);

        return curso;
    }
}