package com.senai.registro_de_acesso_spring.domain.service.JustificativasRegras;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.JustificativaDTO;
import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.OcorrenciaDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.alunoRepositories.JustificativaRepository;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.alunoRepositories.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

public class JustificativaServiceRegras {

    @Autowired
    JustificativaRepository justificativaRepository;

    @Autowired
    OcorrenciaRepository ocorrenciaRepository;

    public Ocorrencia validarOcorrencia (Long id) {
       return ocorrenciaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocorrência com ID" + id + " não encontrado"));
    }

    public void descricaoPreenchida (JustificativaDTO justificativaDTO) {
        if (justificativaDTO.descricao().isEmpty()) {
            ResponseEntity.notFound().build();
        } else  {
            ResponseEntity.ok().build();
        }
    }

    public void limiteJustificativa (OcorrenciaDTO ocorrenciaDTO) {

    }


}
