package com.senai.registro_de_acesso_spring.domain.entity.curso;

import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeCurso;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.id.IntegralDataTypeHolder;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String titulo;

    @Enumerated(EnumType.STRING)
    private TipoDeCurso tipo;

    private Integer cargaHoraria;
    private Integer toleranciaMinutos;

    @OneToMany(mappedBy = "curso")
    private List<UnidadeCurricular> unidadesCurriculares;

    public Curso(String titulo, TipoDeCurso tipo, Integer cargaHoraria, Integer toleranciaMinutos) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.cargaHoraria = cargaHoraria;
        this.toleranciaMinutos = toleranciaMinutos;
    }
}