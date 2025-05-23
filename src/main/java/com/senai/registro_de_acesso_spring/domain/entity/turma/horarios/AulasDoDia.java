package com.senai.registro_de_acesso_spring.domain.entity.turma.horarios;

import com.senai.registro_de_acesso_spring.domain.enuns.DiasDaSemana;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class AulasDoDia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // muta o enum para string
    private DiasDaSemana diaDaSemana;

    @ManyToOne
    @JoinColumn(name = "horario_id")
    private HorarioBase horario;

    @OneToMany(mappedBy = "aulasDia", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderColumn(name = "ordem") // ordenação por ordem alfabetica
    private List<Aula> aulas;
}