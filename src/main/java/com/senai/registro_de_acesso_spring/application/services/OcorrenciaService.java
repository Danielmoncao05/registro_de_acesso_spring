package com.senai.registro_de_acesso_spring.application.services;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Usuario;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.registro_de_acesso_spring.domain.repositories.OcorrenciaRepository;
import com.senai.registro_de_acesso_spring.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OcorrenciaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void criarOcorrenciaDeAtraso(String idDeAcesso){
        Optional<Usuario> usuarioOpt = usuarioRepository.findByIdAcesso(idDeAcesso);
        if(usuarioOpt.isPresent()){
            if(usuarioOpt.get() instanceof Aluno aluno){
                System.out.println("é aluno");
            }
        }
    }

    public void autorizaSaida(){}

}
