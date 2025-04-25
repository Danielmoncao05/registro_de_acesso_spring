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
public class Curso { // obs: Strings temporários, execeto nome
    private List<String> nome;
    private Enum<TipoCurso> tipo;
    private String carga_horaria;
    private List<UnidadeCurricular> unidades_curriculares;
    @Getter
    @Setter
    private static double tolerancia;
}