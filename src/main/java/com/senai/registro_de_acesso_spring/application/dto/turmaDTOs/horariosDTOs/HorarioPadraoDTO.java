package com.senai.registro_de_acesso_spring.application.dto.turmaDTOs.horariosDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.HorarioPadrao;

import java.util.List;

public record HorarioPadraoDTO(
        Long id,
        List<AulasDoDiaDTO> listaDeAulasDoDia
) {
    public static HorarioPadraoDTO toDTO(HorarioPadrao horario) {
        return new HorarioPadraoDTO(
                horario.getId(),
                horario.getListaDeAulasDoDia().stream().map(AulasDoDiaDTO::toDTO).toList()
        );
    }

    public HorarioPadrao fromDTO() {
        HorarioPadrao horario = new HorarioPadrao();

        horario.setListaDeAulasDoDia(
                listaDeAulasDoDia.stream().map(AulasDoDiaDTO::fromDTO).toList()
        );

        return horario;
    }
}