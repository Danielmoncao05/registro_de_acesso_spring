package com.senai.registro_de_acesso_spring.domain.entity.turma;

import com.senai.registro_de_acesso_spring.domain.entity.curso.Curso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Turma { // obs: Strings temporários, execeto nome
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String nome;
    private String periodo;
    private LocalDate dataInicial;
    private LocalTime horarioEntrada;
    private Integer quantidadeSemestres;
    private Integer quantidadeAulasPorDia;

    @ManyToOne
    private Curso curso;

    @OneToMany
    private List<SubTurma> subTurma;
}