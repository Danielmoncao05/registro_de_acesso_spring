package com.senai.registro_de_acesso_spring.application.services;


import com.senai.registro_de_acesso_spring.application.dto.AmbienteDTO;
import com.senai.registro_de_acesso_spring.domain.entity.curso.Ambiente;
import com.senai.registro_de_acesso_spring.domain.repositories.AmbienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmbienteService {
    @Autowired
    private AmbienteRepository ambienteRepository;

    public void adicionarAmbiente(AmbienteDTO ambienteDTO) {
        Ambiente ambiente = new Ambiente();
        ambiente.setNome(ambienteDTO.nome());
        ambienteRepository.save(ambiente);
    }

    public List<AmbienteDTO> listarAmbientes() {
        return ambienteRepository.findAll().stream()
                .map(am -> new AmbienteDTO(am.getId(), am.getNome()))
                .toList();

    }

    public Optional<AmbienteDTO> buscarAmbientePorId(Long id) {
        return ambienteRepository.findById(id).map(am -> new AmbienteDTO(am.getId(), am.getNome()));
    }

    public boolean atualizarAmbientePorId(Long id, AmbienteDTO ambienteDTO) {
        return ambienteRepository.findById(id).map(am -> {
            am.setNome(ambienteDTO.nome());
            ambienteRepository.save(am);
            return true;
        }).orElse(false);
    }

    public boolean deletarAmbiente(Long id){
        if (ambienteRepository.existsById(id)){
            ambienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
