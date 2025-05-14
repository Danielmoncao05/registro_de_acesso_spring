package com.senai.registro_de_acesso_spring.application.services.usuariosServices.alunoServices;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.JustificativaDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.registro_de_acesso_spring.domain.enuns.StatusDaJustificativa;
import com.senai.registro_de_acesso_spring.domain.repositories.usuariosRepositories.alunoRepositories.JustificativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JustificativaService {
    @Autowired
    private JustificativaRepository justificativaRepository;

    public void cadastrarJustificativa(JustificativaDTO dto) {
        justificativaRepository.save(dto.fromDTO());
    }

    public List<JustificativaDTO> listarJustificativas() {
        return justificativaRepository.findByAtivoTrue()// justificativaRepository.findByStatusDaJustificativa(StatusDaJustificativa.APROVADO)
                .stream().map(JustificativaDTO::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<JustificativaDTO> buscarJustificativaPorId(Long id) {
        return justificativaRepository.findById(id)
                .filter(Justificativa::isAtivo) // .filter(justificativa -> justificativa.getStatus() == StatusDaJustificativa.APROVADO)
                .map(JustificativaDTO::toDTO);
    }

    public boolean atualizarJustificativa(Long id, JustificativaDTO dto) {
        return justificativaRepository.findById(id).map(justificativa -> {
            Justificativa justificativaAtualizada = dto.fromDTO();

            justificativa.setTipo(justificativaAtualizada.getTipo());
            justificativa.setDescricao(justificativaAtualizada.getDescricao());
            justificativa.setAnexo(justificativaAtualizada.getAnexo());
            justificativa.setDataInicial(justificativaAtualizada.getDataInicial());
            justificativa.setQuantidadeDias(justificativaAtualizada.getQuantidadeDias());
            justificativa.setAluno(justificativaAtualizada.getAluno());

            justificativaRepository.save(justificativa);
            return true;
        }).orElse(false);
    }

    public boolean inativarJustificativa(Long id) {
        return justificativaRepository.findById(id).map(justificativa -> {
            justificativa.setStatus(StatusDaJustificativa.REPROVADO);

            justificativaRepository.save(justificativa);
            return true;
        }).orElse(false);
    }
}