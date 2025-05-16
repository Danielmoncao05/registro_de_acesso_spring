package com.senai.registro_de_acesso_spring.application.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record TurmaDTO(
        Long id,
        String nome,
        String periodo,
        LocalDate dataInicial,
        LocalTime horarioEntrada,
        Integer quantidadeSemestres,
        Integer quantidadeAulasPorDia) {
}
