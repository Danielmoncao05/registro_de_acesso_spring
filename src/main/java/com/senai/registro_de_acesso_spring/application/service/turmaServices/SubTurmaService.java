package com.senai.registro_de_acesso_spring.application.service.turmaServices;

import com.senai.registro_de_acesso_spring.application.dto.turmaDTOs.SubTurmaDTO;
import com.senai.registro_de_acesso_spring.domain.entity.turma.Semestre;
import com.senai.registro_de_acesso_spring.domain.entity.turma.SubTurma;
import com.senai.registro_de_acesso_spring.domain.entity.turma.Turma;
import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.HorarioPadrao;
import com.senai.registro_de_acesso_spring.domain.repository.turmaRepositories.SubTurmaRepository;
import com.senai.registro_de_acesso_spring.domain.repository.turmaRepositories.TurmaRepository;
import com.senai.registro_de_acesso_spring.domain.service.horarioService.HorarioServiceDomain;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubTurmaService {
    @Autowired
    private SubTurmaRepository subTurmaRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private HorarioServiceDomain horarioService;

    @Transactional
    public void criarSubTurma(Long turmaId) {
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        SubTurma subTurma = new SubTurma();
        subTurma.setTurma(turma);
        subTurma.setNome("Turma "+subTurma.getTurma().getSubTurma().size());

        turma.getSubTurma().add(subTurma);

        Semestre semestre = new Semestre();
        subTurma.setSemestres(new ArrayList<>());
        subTurma.getSemestres().add(semestre);

        semestre.setNumero(subTurma.getSemestres().size());
        semestre.setNomeDaTurma(
                subTurma.getSemestres().size() +
                        subTurma.getTurma().getSiglaDaTurma() +
                        subTurma.getTurma().getPeriodo().getSigla()
        );
        semestre.setSubTurma(subTurma);

        // Criar HorarioPadrao vazio
        HorarioPadrao horarioPadrao = horarioService.criarHorarioPadraoVazio(semestre);
        semestre.setHorarioPadrao(horarioPadrao);

        semestre.setHorariosSemanais(new ArrayList<>());
        subTurma.setAlunos(new ArrayList<>());

        subTurmaRepository.save(subTurma);
    }

    public List<SubTurmaDTO> listar() {
        return subTurmaRepository.findAll().stream()
                .map(SubTurmaDTO::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<SubTurmaDTO> buscarPorId(Long id) {
        return subTurmaRepository.findById(id).map(SubTurmaDTO::toDTO);
    }

    @Transactional
    public boolean atualizar(Long id, SubTurmaDTO dto) {
        Optional<SubTurma> optional = subTurmaRepository.findById(id);
        if (optional.isEmpty()) return false;

        SubTurma subTurma = optional.get();
        subTurma.setNome(dto.nome());

        subTurmaRepository.save(subTurma);
        return true;
    }

    @Transactional
    public boolean deletar(Long id) {
        if (!subTurmaRepository.existsById(id)) return false;
        subTurmaRepository.deleteById(id);
        return true;
    }
}
