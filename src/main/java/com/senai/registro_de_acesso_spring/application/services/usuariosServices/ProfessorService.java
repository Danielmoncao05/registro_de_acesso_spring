package com.senai.registro_de_acesso_spring.application.services.usuariosServices;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.ProfessorDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import com.senai.registro_de_acesso_spring.domain.repositories.usuariosRepositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {
    @Autowired
    ProfessorRepository professorRepository;

    public void cadastrarProfessor(ProfessorDTO dto) {
        professorRepository.save(dto.fromDTO());
    }

    public List<ProfessorDTO> listarProfessores() {
        return professorRepository.findByAtivoTrue()
                .stream().map(ProfessorDTO::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProfessorDTO> buscarProfessorPorId(Long id) {
        return professorRepository.findById(id)
                .filter(Professor::isAtivo)
                .map(ProfessorDTO::toDTO);
    }

    public boolean atualizarProfessor(Long id, ProfessorDTO dto) {
        return professorRepository.findById(id).map(professor -> {
            Professor professorAtualizado = dto.fromDTO();

            professor.setNome(professorAtualizado.getNome());
            professor.setEmail(professorAtualizado.getEmail());
            professor.setDataNascimento(professorAtualizado.getDataNascimento());
            professor.setCpf(professorAtualizado.getCpf());

            professorRepository.save(professor);
            return true;
        }).orElse(false);
    }

    public boolean inativarProfessor(Long id) {
        return professorRepository.findById(id).map(professor -> {
            professor.setAtivo(false);

            professorRepository.save(professor);
            return true;
        }).orElse(false);
    }


}