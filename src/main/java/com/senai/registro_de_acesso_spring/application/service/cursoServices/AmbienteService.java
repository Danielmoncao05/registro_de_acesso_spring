package com.senai.registro_de_acesso_spring.application.service.cursoServices;

import com.senai.registro_de_acesso_spring.application.dto.cursoDTOs.AmbienteDTO;
import com.senai.registro_de_acesso_spring.domain.entity.curso.Ambiente;
import com.senai.registro_de_acesso_spring.domain.repository.cursoRepositories.AmbienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AmbienteService {
    @Autowired
    AmbienteRepository ambienteRepository;

    public void cadastrarAmbiente(AmbienteDTO dto) {
        ambienteRepository.save(dto.fromDTO());
    }

    public List<AmbienteDTO> listarAmbientes() {
        return ambienteRepository.findByAtivoTrue()
                .stream().map(AmbienteDTO::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AmbienteDTO> buscarAmbientePorId(Long id) {
        return ambienteRepository.findById(id)
                .filter(Ambiente::isAtivo)
                .map(AmbienteDTO::toDTO);
    }

    public boolean atualizarAmbiente(Long id, AmbienteDTO dto) {
        return ambienteRepository.findById(id).map(ambiente -> {
            Ambiente ambienteAtualizado = dto.fromDTO();

            ambiente.setNome(ambienteAtualizado.getNome());

            ambienteRepository.save(ambiente);
            return true;
        }).orElse(false);
    }

    public boolean inativarAmbiente(Long id) {
        return ambienteRepository.findById(id).map(ambiente -> {
            ambiente.setAtivo(false);

            ambienteRepository.save(ambiente);
            return true;
        }).orElse(false);
    }
}