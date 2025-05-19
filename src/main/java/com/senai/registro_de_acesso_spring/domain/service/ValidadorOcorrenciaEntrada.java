package com.senai.registro_de_acesso_spring.domain.service;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.repositories.usuariosRepositories.UsuarioRepository;
import com.senai.registro_de_acesso_spring.domain.repositories.usuariosRepositories.alunoRepositories.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidadorOcorrenciaEntrada {
    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar Ocorrencia de Atraso com MQTT
    public void criarOcorrenicaDeAtraso(String idAcesso) {
        usuarioRepository.findByIdAcesso(idAcesso).map(usuario -> {
            if(usuario instanceof Aluno) {
                System.out.println("Aluno");
            } else {
                System.out.println("Não é Aluno");
            }
            return null;
        });
    }
}