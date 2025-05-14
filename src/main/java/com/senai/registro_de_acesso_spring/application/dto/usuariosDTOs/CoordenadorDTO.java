package com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Coordenador;
import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeUsuario;

import java.time.LocalDate;

public record CoordenadorDTO(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        TipoDeUsuario tipoDeUsuario
) {
    public static CoordenadorDTO toDTO(Coordenador c) {
        TipoDeUsuario tipo = TipoDeUsuario.COORDENADOR;
        return new CoordenadorDTO(c.getId(), c.getNome(), c.getCpf(), c.getDataNascimento(), c.getEmail(), tipo);
    }

    public Coordenador fromDTO() {
        Coordenador coordenador = new Coordenador();

        coordenador.setId(id);
        coordenador.setNome(nome);
        coordenador.setCpf(cpf);
        coordenador.setEmail(email);
        coordenador.setDataNascimento(dataNascimento);
        coordenador.setAtivo(true);
        coordenador.setIdAcesso("");
        coordenador.setSenha("");

        return coordenador;
    }
}