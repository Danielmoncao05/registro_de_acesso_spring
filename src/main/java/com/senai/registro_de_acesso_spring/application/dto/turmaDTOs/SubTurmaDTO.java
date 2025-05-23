package com.senai.registro_de_acesso_spring.application.dto.turmaDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.turma.Semestre;
import com.senai.registro_de_acesso_spring.domain.entity.turma.SubTurma;
import com.senai.registro_de_acesso_spring.domain.entity.turma.Turma;
import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.HorarioPadrao;
import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.HorarioSemanal;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;

import java.util.ArrayList;
import java.util.List;

public record SubTurmaDTO(
        Long id,
        String nome,
        Turma turma,
        List<Aluno> aluno,
        List<Semestre> semestres
) {
    public static SubTurmaDTO toDTO(SubTurma sbt) {
        return new SubTurmaDTO(
                sbt.getId(),
                sbt.getNome(),
                sbt.getTurma(),
                sbt.getAlunos(),
                sbt.getSemestres()
        );
    }

    public SubTurma fromDTO() {
        SubTurma subTurma = new SubTurma();

        subTurma.setId(id);
        subTurma.setNome(nome);
        subTurma.setAlunos(aluno);
        subTurma.setSemestres(semestres);

        return subTurma;
    }
}