package com.senai.registro_de_acesso_spring.domain.entity.curso;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class UnidadeCurricular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String nome;
    private Integer cargaHoraria;

    @ManyToOne
    private Curso curso;

    @ManyToMany
    private List<Professor> professor;

    public UnidadeCurricular(String nome, Integer cargaHoraria) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }
}