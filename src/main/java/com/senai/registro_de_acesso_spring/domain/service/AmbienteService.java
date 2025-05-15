package com.senai.registro_de_acesso_spring.domain.service;

import com.senai.registro_de_acesso_spring.application.dto.AmbienteDTO;
import com.senai.registro_de_acesso_spring.domain.entity.curso.Ambiente;
import com.senai.registro_de_acesso_spring.domain.repositories.AmbienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmbienteService {
    @Autowired
    private AmbienteRepository ambienteRepository;

    public boolean deletarAmbiente(long id){
        if (ambienteRepository.existsById(id)){
            ambienteRepository.deleteById(id);
            return true;
        }
        return true;
    }

    public void criarAmbiente(AmbienteDTO ambienteDTO){
        Ambiente ambiente = new Ambiente();
        ambiente.setNome(ambienteDTO.nome());
        ambienteRepository.save(ambiente);
    }

    public List<AmbienteDTO> listarAmbientes(){
        return ambienteRepository.findAll().stream()
                .map(am -> new AmbienteDTO(am.getId(),am.getNome()))
                .toList();
        )
    }





}
