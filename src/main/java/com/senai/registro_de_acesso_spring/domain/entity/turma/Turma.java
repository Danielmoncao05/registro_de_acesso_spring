package com.senai.registro_de_acesso_spring.domain.entity.turma;

import com.senai.registro_de_acesso_spring.domain.entity.curso.Curso;
import com.senai.registro_de_acesso_spring.domain.enums.Periodo;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String siglaDaTurma;
    private Periodo periodo;
    private LocalDate dataInicial;
    private LocalTime horarioEntrada;
    private Integer quantidadeSemestres;
    private Integer quantidadeAulasPorDia;
    private boolean ativo;

    @ManyToOne
    private Curso curso;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubTurma> subTurma;
}