package com.senai.registro_de_acesso_spring.domain.entity.turma.horarios;

import com.senai.registro_de_acesso_spring.domain.entity.curso.Ambiente;
import com.senai.registro_de_acesso_spring.domain.entity.curso.UnidadeCurricular;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ordem;

    @ManyToOne
    private AulasDoDia aulasDia;

    @ManyToOne
    private UnidadeCurricular unidadeCurricular;

    @ManyToOne
    private Professor professor;

    @ManyToOne
    private Ambiente ambiente;
}