package com.senai.registro_de_acesso_spring.domain.service;

import com.senai.registro_de_acesso_spring.domain.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository turmaRepository;
}
