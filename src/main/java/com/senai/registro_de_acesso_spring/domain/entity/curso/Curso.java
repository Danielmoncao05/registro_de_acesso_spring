package com.senai.registro_de_acesso_spring.domain.entity.curso;

import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeCurso;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
public class Curso { // obs: Strings temporários, execeto nome
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String titulo;

    @Enumerated(EnumType.STRING)
    private TipoDeCurso tipo;

    private Integer carga_horaria;
    private Integer toleranciaMinutos;
    private Integer quantidadeSemestres;

    @OneToMany(mappedBy = "curso")
    private List<UnidadeCurricular> unidades_curriculares;
}