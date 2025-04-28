package com.senai.registro_de_acesso_spring.domain.entities_BAGUNCADO;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Turma { // obs: Strings temporários, execeto nome
    private String nome;
    private List<SubTurma> subTurma;
    private Periodo periodo;
    private Curso curso;
    private String data_inicial;
    private String horario_entrada;
    private String quantidade_semestres;
}