package com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno;

import com.senai.registro_de_acesso_spring.domain.enums.StatusDaJustificativa;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeJustifcativa;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
public class Justificativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoDeJustifcativa tipo;

    private String descricao;
    private String anexo;
    private LocalDate dataInicial;
    private Integer quantidadeDias;

    @Enumerated(EnumType.STRING)
    private StatusDaJustificativa status;

    @ManyToOne
    private Aluno aluno;
}