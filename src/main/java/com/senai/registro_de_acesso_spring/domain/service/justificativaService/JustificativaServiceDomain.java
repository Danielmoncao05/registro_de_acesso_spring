package com.senai.registro_de_acesso_spring.domain.service.justificativaService;

import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.alunoRepositories.AlunoRepository;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.alunoRepositories.JustificativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JustificativaServiceDomain {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private JustificativaRepository justificativaRepository;
}