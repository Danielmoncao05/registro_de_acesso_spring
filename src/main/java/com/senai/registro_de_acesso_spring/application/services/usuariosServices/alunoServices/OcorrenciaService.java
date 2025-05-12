package com.senai.registro_de_acesso_spring.application.services.usuariosServices.alunoServices;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.OcorrenciaDTO;
import com.senai.registro_de_acesso_spring.domain.repositories.usuariosRepositories.alunoRepositories.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OcorrenciaService {
    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    public void cadastrarOcorrencia(OcorrenciaDTO dto) {
        ocorrenciaRepository.save(dto.fromDTO());
    }

    public List<OcorrenciaDTO> listarOcorrencia() {
        return ocorrenciaRepository.findAll()
                .stream().map(OcorrenciaDTO::toDTO)
                .collect(Collectors.toList());
    }


}