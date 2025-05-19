package com.senai.registro_de_acesso_spring.application.services;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Usuario;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.repositories.OcorrenciaRepository;
import com.senai.registro_de_acesso_spring.domain.repositories.UsuarioRepository;
import com.senai.registro_de_acesso_spring.domain.service.ValidadorOcorrencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OcorrenciaService {
    @Autowired
    private OcorrenciaRepository ocorrenciaRepo;
    @Autowired
    private UsuarioRepository usuarioRepo;
    @Autowired
    private ValidadorOcorrencia validadorOcorrencia;

    public void criarOcorrenciaAtraso(String idAcesso){
        Optional<Usuario> usuarioOpt = usuarioRepo.findByIdAcesso(idAcesso);
        if (usuarioOpt.isPresent()){
            if (usuarioOpt.get() instanceof Aluno aluno){
                System.out.println("Aluno identificado portador do ID de acesso '" + idAcesso + "'!");
            }
        }
    }
}
