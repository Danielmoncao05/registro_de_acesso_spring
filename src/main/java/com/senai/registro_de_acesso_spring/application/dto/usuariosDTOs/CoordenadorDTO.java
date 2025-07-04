package com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Coordenador;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeUsuario;

import java.time.LocalDate;

public record CoordenadorDTO(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        String senha,
        String telefone,
        String idAcesso,
        String username,
        TipoDeUsuario tipo
) {
    public static CoordenadorDTO toDTO(Coordenador c) {
        TipoDeUsuario tipo = TipoDeUsuario.COORDENADOR;
        return new CoordenadorDTO(
                c.getId(),
                c.getNome(),
                c.getCpf(),
                c.getDataNascimento(),
                c.getEmail(),
                c.getSenha(),
                c.getTelefone(),
                c.getIdAcesso(),
                c.getUsername(),
                tipo);
    }

    public Coordenador fromDTO() {
        Coordenador c = new Coordenador();

        c.setId(id);
        c.setNome(nome);
        c.setCpf(cpf);
        c.setDataNascimento(dataNascimento);
        c.setEmail(email);
        c.setSenha(senha);
        c.setTelefone(telefone);
        c.setIdAcesso(idAcesso);
        c.setUsername(username);
        c.setAtivo(true);

        return c;
    }
}