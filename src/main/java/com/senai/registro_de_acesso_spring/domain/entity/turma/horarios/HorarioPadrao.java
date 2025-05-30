package com.senai.registro_de_acesso_spring.domain.entity.turma.horarios;

import com.senai.registro_de_acesso_spring.domain.entity.turma.Semestre;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class HorarioPadrao extends HorarioBase{
    @OneToOne(mappedBy = "horarioPadrao")
    private Semestre semestre;
}