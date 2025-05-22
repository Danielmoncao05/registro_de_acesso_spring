package com.senai.registro_de_acesso_spring.domain.service;


import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.AlunoDTO;
import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeJustifcativa;
import com.senai.registro_de_acesso_spring.domain.repositories.usuariosRepositories.alunoRepositories.JustificativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidadorJustificativa {
    @Autowired
    private JustificativaRepository justificativaRepository;

    public void ada(AlunoDTO dto, TipoDeJustifcativa tipo) {
        if(tipo.equals(TipoDeJustifcativa.ATRASO)) {
            criarJustificativaAtraso(dto);
        } else if(tipo.equals(TipoDeJustifcativa.FALTA)) {
            criarJustificativaFalta(dto);
        }
    }

    public void criarJustificativaAtraso(AlunoDTO dto) {

    }

    public void criarJustificativaFalta(AlunoDTO dto) {}
}