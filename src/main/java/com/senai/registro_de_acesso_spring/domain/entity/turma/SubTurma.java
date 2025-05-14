package com.senai.registro_de_acesso_spring.domain.entity.turma;

import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.HorarioPadrao;
import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.HorarioSemanal;
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
    private Turma turma;

    @ManyToMany(mappedBy = "subTurmas")
    private List<Aluno> aluno;

    @OneToOne(cascade = CascadeType.ALL) // um paara um nãi necessita de tabela intermediaria
    @JoinColumn(name = "horario_padrao_id")
    private HorarioPadrao horarioPadrao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_turma_id")
    private List<HorarioSemanal> horariosSemanais;
}