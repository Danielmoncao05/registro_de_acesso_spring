package com.senai.registro_de_acesso_spring.domain.service.ocorrenciaService;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OcorrenciaServiceDomain {
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar Ocorrencia de Atraso com MQTT
    public boolean criarOcorrenicaDeAtraso(String idAcesso) {
        usuarioRepository.findByIdAcesso(idAcesso).map(usuario -> {
            if (usuario instanceof Aluno) {
                System.out.println("È Aluno");
                return true;
            } else {
                System.out.println("Não é Aluno");
                return  false;
            }
        });
        return false;
    }


}