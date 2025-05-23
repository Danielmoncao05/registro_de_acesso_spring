package com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno;

import com.senai.registro_de_acesso_spring.domain.entity.curso.UnidadeCurricular;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import com.senai.registro_de_acesso_spring.domain.enuns.StatusDaOcorrencia;
import com.senai.registro_de_acesso_spring.domain.enuns.StatusDaJustificativa;
import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeOcorrencia;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
public class Ocorrencia { // obs: Strings temporários
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(EnumType.STRING)
    private TipoDeOcorrencia tipo;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusDaOcorrencia status;

    private LocalDateTime dataHoraCriacao;
    private LocalDateTime dataHoraConclusao;
    private boolean ativo;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Professor professorResponsavel;

    @ManyToOne
    private UnidadeCurricular unidadeCurricular;
}