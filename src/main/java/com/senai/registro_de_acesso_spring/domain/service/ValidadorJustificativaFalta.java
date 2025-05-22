package com.senai.registro_de_acesso_spring.domain.service;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.AlunoDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.repositories.usuariosRepositories.UsuarioRepository;
import com.senai.registro_de_acesso_spring.domain.repositories.usuariosRepositories.alunoRepositories.JustificativaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorJustificativaFalta {
    @Autowired
    private JustificativaRepository justificativaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public void criarJustificativaFalta(AlunoDTO dto) {
        if(verificarAluno(dto.idAcesso())) {}
    }

    private boolean verificarAluno(String idAcesso) {
        usuarioRepository.findByIdAcesso(idAcesso).map(usuario -> {
            if(usuario instanceof Aluno) {
                System.out.println("Aluno");
                return true;
            } else {
                System.out.println("Não é Aluno");
                return false;
            }
        });
        return false;
    }
}