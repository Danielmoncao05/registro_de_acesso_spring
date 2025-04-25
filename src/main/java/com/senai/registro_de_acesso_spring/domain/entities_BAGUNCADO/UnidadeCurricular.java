package com.senai.registro_de_acesso_spring.domain.entities_BAGUNCADO;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnidadeCurricular { // obs: Strings temporários, execeto nome
    private String nome;
    private List<Professor> professor;
    private String carga_horaria;
}