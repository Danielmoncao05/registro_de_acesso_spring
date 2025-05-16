package com.senai.registro_de_acesso_spring.domain.service;

import com.senai.registro_de_acesso_spring.application.dto.JustificativaDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.registro_de_acesso_spring.domain.repositories.JustificativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JustificativaService {

    @Autowired
    public JustificativaRepository justificativaRepo;

    public void registrarJustificativa(JustificativaDTO justificativaDTO) {
        justificativaRepo.save(justificativaDTO.fromDTO());
    }

    public Optional<JustificativaDTO> buscarPorId(Long id) {
        return justificativaRepo.findById(id).map(JustificativaDTO::toDTO);
    }

    public List<JustificativaDTO> listar() {
        return justificativaRepo.findAll().stream().map(JustificativaDTO::toDTO).toList();
    }

    public boolean atualizar(Long id, JustificativaDTO justificativaDTO) {
        return justificativaRepo.findById(id).map(justificativa -> {
            Justificativa justificativaAtualizado = justificativaDTO.fromDTO();
            justificativa.setTipo(justificativaAtualizado.getTipo());
            justificativa.setDescricao(justificativaAtualizado.getDescricao());
            justificativa.setAnexo(justificativaAtualizado.getAnexo());
            justificativa.setDataInicial(justificativaAtualizado.getDataInicial());
            justificativa.setQuantidadeDias(justificativaAtualizado.getQuantidadeDias());
            justificativa.setStatus(justificativaAtualizado.getStatus());
            return true;
        }).orElse(false);
    }


    public boolean deletarJustificativa(Long id) {
        if(justificativaRepo.existsById(id)) {
            justificativaRepo.deleteById(id);
            return true;
        }else  {
            return false;
        }
    }
}

