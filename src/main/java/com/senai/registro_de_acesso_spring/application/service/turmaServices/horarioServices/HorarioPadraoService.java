package com.senai.registro_de_acesso_spring.application.service.turmaServices.horarioServices;

import com.senai.registro_de_acesso_spring.application.dto.turmaDTOs.horariosDTOs.HorarioPadraoDTO;
import com.senai.registro_de_acesso_spring.domain.entity.turma.Semestre;
import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.HorarioPadrao;
import com.senai.registro_de_acesso_spring.domain.repository.turmaRepositories.SemestreRepository;
import com.senai.registro_de_acesso_spring.domain.repository.turmaRepositories.horariosRepositories.HorarioPadraoRepository;
import com.senai.registro_de_acesso_spring.domain.service.horarioService.HorarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioPadraoService {

    @Autowired
    private HorarioPadraoRepository horarioPadraoRepository;

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private SemestreRepository semestreRepository;

    @Transactional
    public void salvarHorarioPadrao(Long semestreId, HorarioPadraoDTO dto) {
        Semestre semestre = semestreRepository.findById(semestreId)
                .orElseThrow(() -> new IllegalArgumentException("Semestre não encontrado"));

        HorarioPadrao horario = semestre.getHorarioPadrao();

        horarioService.preencherHorario(horario, dto.listaDeAulasDoDia());
        horarioPadraoRepository.save(horario);
    }

    public List<HorarioPadraoDTO> listar() {
        return horarioPadraoRepository.findAll().stream()
                .map(HorarioPadraoDTO::toDTO).toList();
    }

    public Optional<HorarioPadraoDTO> buscarPorId(Long id) {
        return horarioPadraoRepository.findById(id).map(HorarioPadraoDTO::toDTO);
    }

    @Transactional
    public boolean atualizar(Long id, HorarioPadraoDTO dto) {
        Optional<HorarioPadrao> optional = horarioPadraoRepository.findById(id);
        if (optional.isEmpty()) return false;

        HorarioPadrao horario = optional.get();
        horarioService.preencherHorario(horario, dto.listaDeAulasDoDia());
        horarioPadraoRepository.save(horario);
        return true;
    }

    public boolean deletar(Long id) {
        if (!horarioPadraoRepository.existsById(id)) return false;
        horarioPadraoRepository.deleteById(id);
        return true;
    }
}
