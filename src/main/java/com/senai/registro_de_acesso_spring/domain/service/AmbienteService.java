package com.senai.registro_de_acesso_spring.domain.service;

import com.senai.registro_de_acesso_spring.application.dto.AmbienteDTO;
import com.senai.registro_de_acesso_spring.domain.entity.curso.Ambiente;
import com.senai.registro_de_acesso_spring.domain.repositories.AmbienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AmbienteService {
    @Autowired
    private AmbienteRepository ambienteRepository;

}
