package com.senai.registro_de_acesso_spring.application.dto;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Coordenador;
import java.time.LocalDate;


public record CoordenadorDTO (
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        String telefone)
{
    public static CoordenadorDTO toDTO(Coordenador c) {
        return new CoordenadorDTO(c.getId(), c.getNome(), c.getCpf(), c.getDataNascimento(), c.getEmail(),c.getTelefone());
    }

    public Coordenador fromDTO() {
        Coordenador c = new Coordenador();
        c.setId(id);
        c.setNome(nome);
        c.setCpf(cpf);
        c.setTelefone(telefone);
        c.setEmail(email);
        c.setDataNascimento(dataNascimento);
        c.setAtivo(true);
        return c;
    }
}
