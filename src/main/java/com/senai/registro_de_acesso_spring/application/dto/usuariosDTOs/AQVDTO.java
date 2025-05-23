package com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.AQV;
import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeUsuario;

import java.time.LocalDate;

public record AQVDTO (
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        String senha,
        String telefone,
        String idAcesso,
        TipoDeUsuario tipo
){
    public static AQVDTO toDTO(AQV a) {
        TipoDeUsuario tipo = TipoDeUsuario.AQV;
        return new AQVDTO(
                a.getId(),
                a.getNome(),
                a.getCpf(),
                a.getDataNascimento(),
                a.getEmail(),
                a.getSenha(),
                a.getTelefone(),
                a.getIdAcesso(),
                tipo
        );
    }

    public AQV fromDTO() {
        AQV aqv = new AQV();

        aqv.setId(id);
        aqv.setNome(nome);
        aqv.setCpf(cpf);
        aqv.setDataNascimento(dataNascimento);
        aqv.setEmail(email);
        aqv.setSenha(senha);
        aqv.setTelefone(telefone);
        aqv.setIdAcesso(idAcesso);
        aqv.setAtivo(true);
        aqv.setFoto("");

        return aqv;
    }
}