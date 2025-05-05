package com.senai.registro_de_acesso_spring.domain.enuns;

import lombok.Data;

//@Data
public enum TipoDeCurso {
    CAI(20,50), TECNICO(15,45);

    private final int intervaloMinutos;
    private final int minutosPorAula;

    TipoDeCurso(int intervaloMinutos, int minutosPorAula) {
        this.intervaloMinutos = intervaloMinutos;
        this.minutosPorAula = minutosPorAula;
    }

    public int getIntervaloMinutos() { return intervaloMinutos; }

    public int getMinutosPorAula() { return minutosPorAula; }
}