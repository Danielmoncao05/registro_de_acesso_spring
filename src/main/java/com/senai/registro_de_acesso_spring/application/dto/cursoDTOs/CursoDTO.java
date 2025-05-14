package com.senai.registro_de_acesso_spring.application.dto.cursoDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.curso.Curso;
import com.senai.registro_de_acesso_spring.domain.entity.curso.UnidadeCurricular;
import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeCurso;

import java.util.ArrayList;
import java.util.List;

public record CursoDTO(
        Long id,
        String titulo,
        TipoDeCurso tipo,
        Integer carga_horaria,
        Integer toleranciaMinutos,
        Integer quantidadeSemestres,
        List<UnidadeCurricular> unidades_curriculares
) {
    public static CursoDTO toDTO(Curso c) {
        List<UnidadeCurricular> unidades_curriculares = new ArrayList<>();
        return new CursoDTO(c.getId(), c.getTitulo(), c.getTipo(), c.getCarga_horaria(), c.getToleranciaMinutos(), c.getQuantidadeSemestres(), unidades_curriculares);
    }

    public Curso fromDTO() {
        Curso curso = new Curso();

        curso.setId(id);
        curso.setTitulo(titulo);
        curso.setTipo(tipo);
        curso.setCarga_horaria(carga_horaria);
        curso.setToleranciaMinutos(toleranciaMinutos);
        curso.setQuantidadeSemestres(quantidadeSemestres);
        curso.setUnidades_curriculares(unidades_curriculares);

        return curso;
    }
}