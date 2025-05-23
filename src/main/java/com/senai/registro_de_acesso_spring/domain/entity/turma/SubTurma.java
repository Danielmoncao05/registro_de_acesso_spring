package com.senai.registro_de_acesso_spring.domain.entity.turma;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity
@Data
public class SubTurma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    @OneToMany(mappedBy = "subTurma", cascade = CascadeType.ALL)
    private List<Aluno> alunos;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Semestre> semestres;

}