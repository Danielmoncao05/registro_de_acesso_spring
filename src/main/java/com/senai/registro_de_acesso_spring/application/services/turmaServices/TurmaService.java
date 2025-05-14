package com.senai.registro_de_acesso_spring.application.services.turmaServices;

import com.senai.registro_de_acesso_spring.application.dto.turmaDTOs.TurmaDTO;
import com.senai.registro_de_acesso_spring.domain.entity.turma.Turma;
import com.senai.registro_de_acesso_spring.domain.repositories.turmaRepositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurmaService {
    @Autowired
    TurmaRepository turmaRepository;

    public void cadastrarTurma(TurmaDTO dto) {
        turmaRepository.save(dto.fromDTO());
    }

    public List<TurmaDTO> listarTurmas() {
        return turmaRepository.findByAtivoTrue()
                .stream().map(TurmaDTO::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<TurmaDTO> buscarTurmaPorId(Long id) {
        return turmaRepository.findById(id)
                .filter(Turma::isAtivo)
                .map(TurmaDTO::toDTO);
    }

    public boolean atualizarTurma(Long id, TurmaDTO dto) {
        return turmaRepository.findById(id).map(turma -> {
            Turma turmaAtualizada = dto.fromDTO();

            turma.setId(turmaAtualizada.getId());
            turma.setNome(turmaAtualizada.getNome());
            turma.setPeriodo(turmaAtualizada.getPeriodo());
            turma.setDataInicial(turmaAtualizada.getDataInicial());
            turma.setHorarioEntrada(turmaAtualizada.getHorarioEntrada());
            turma.setQuantidadeSemestres(turmaAtualizada.getQuantidadeSemestres());
            turma.setQuantidadeAulasPorDia(turmaAtualizada.getQuantidadeAulasPorDia());
            turma.setCurso(turmaAtualizada.getCurso());
            turma.setSubTurma(turmaAtualizada.getSubTurma());

            turmaRepository.save(turma);
            return true;
        }).orElse(false);
    }

    public boolean inativarTurma(Long id) {
        return turmaRepository.findById(id).map(turma -> {
            turma.setAtivo(false);

            turmaRepository.save(turma);
            return true;
        }).orElse(false);
    }
}