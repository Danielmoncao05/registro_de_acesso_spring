package com.senai.registro_de_acesso_spring.application.dto.cursoDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.curso.Ambiente;

public record AmbienteDTO(
        Long id,
        String nome
) {
    public static AmbienteDTO toDTO(Ambiente a) {
        return new AmbienteDTO(a.getId(), a.getNome());
    }

    public Ambiente fromDTO() {
        Ambiente ambiente = new Ambiente();

        ambiente.setId(id);
        ambiente.setNome(nome);

        return ambiente;
    }
}