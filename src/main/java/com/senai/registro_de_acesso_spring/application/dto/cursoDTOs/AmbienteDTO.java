package com.senai.registro_de_acesso_spring.application.dto.cursoDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.curso.Ambiente;

public record AmbienteDTO(
        Long id,
        String nome,
        boolean ativo
) {
    public static AmbienteDTO toDTO(Ambiente a) {
        return new AmbienteDTO(
                a.getId(),
                a.getNome(),
                a.isAtivo());
    }

    public Ambiente fromDTO() {
        return new Ambiente(
                id,
                nome,
                true
        );
    }
}