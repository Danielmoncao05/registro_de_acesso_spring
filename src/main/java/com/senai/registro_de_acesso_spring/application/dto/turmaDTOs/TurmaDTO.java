package com.senai.registro_de_acesso_spring.application.dto.turmaDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.curso.Curso;
import com.senai.registro_de_acesso_spring.domain.entity.turma.SubTurma;
import com.senai.registro_de_acesso_spring.domain.entity.turma.Turma;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public record TurmaDTO(
        Long id,
        String nome,
        String periodo,
        LocalDate dataInicial,
        LocalTime horarioEntrada,
        Integer quantidadeSemestres,
        Integer quantidadeAulasPorDia,
        Curso curso,
        List<SubTurma> subTurma
) {
    public static TurmaDTO toDTO(Turma t) {
        List<SubTurma> subTurma = new ArrayList<>();
        return new TurmaDTO(t.getId(), t.getNome(), t.getPeriodo(), t.getDataInicial(), t.getHorarioEntrada(), t.getQuantidadeSemestres(), t.getQuantidadeAulasPorDia(), t.getCurso(), subTurma);
    }

    public Turma fromDTO() {
        Turma turma = new Turma();

        turma.setId(id);
        turma.setNome(nome);
        turma.setPeriodo(periodo);
        turma.setDataInicial(dataInicial);
        turma.setHorarioEntrada(horarioEntrada);
        turma.setQuantidadeSemestres(quantidadeSemestres);
        turma.setQuantidadeAulasPorDia(quantidadeAulasPorDia);
        turma.setCurso(curso);
        turma.setSubTurma(subTurma);

        return turma;
    }
}