package com.senai.registro_de_acesso_spring.application.dto.turmaDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.turma.Turma;
import com.senai.registro_de_acesso_spring.domain.enuns.Periodo;

import java.time.LocalDate;
import java.time.LocalTime;

public record TurmaDTO(
        Long id,
        String siglaDaTurma,
        Periodo periodo,
        LocalDate dataInicial,
        LocalTime horarioEntrada,
        Integer quantidadeSemestres,
        Integer quantidadeAulasPorDia,
        Long cursoId
) {
    public static TurmaDTO toDTO(Turma t) {
        return new TurmaDTO(
                t.getId(),
                t.getSiglaDaTurma(),
                t.getPeriodo(),
                t.getDataInicial(),
                t.getHorarioEntrada(),
                t.getQuantidadeSemestres(),
                t.getQuantidadeAulasPorDia(),
                t.getCurso() != null ? t.getCurso().getId() : null )
        ;
    }

    public Turma fromDTO() {
        Turma turma = new Turma();

        turma.setSiglaDaTurma(siglaDaTurma);
        turma.setPeriodo(periodo);
        turma.setDataInicial(dataInicial);
        turma.setHorarioEntrada(horarioEntrada);
        turma.setQuantidadeSemestres(quantidadeSemestres);
        turma.setQuantidadeAulasPorDia(quantidadeAulasPorDia);

        return turma;
    }
}