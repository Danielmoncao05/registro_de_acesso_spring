package com.senai.registro_de_acesso_spring.domain.service.horarioService;

import com.senai.registro_de_acesso_spring.application.dto.turmaDTOs.horariosDTOs.AulaDTO;
import com.senai.registro_de_acesso_spring.application.dto.turmaDTOs.horariosDTOs.AulasDoDiaDTO;
import com.senai.registro_de_acesso_spring.domain.entity.turma.Semestre;
import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.*;
import com.senai.registro_de_acesso_spring.domain.enums.DiasDaSemana;
import com.senai.registro_de_acesso_spring.domain.repository.cursoRepositories.AmbienteRepository;
import com.senai.registro_de_acesso_spring.domain.repository.cursoRepositories.UnidadeCurricularRepository;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.ProfessorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HorarioService {
    @Autowired
    private UnidadeCurricularRepository unidadeCurricularRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AmbienteRepository ambienteRepository;

    public HorarioPadrao criarHorarioPadraoVazio(Semestre semestre) {
        return criarHorarioVazio(new HorarioPadrao(), semestre, null);
    }

    public HorarioSemanal criarHorarioSemanalVazio(Semestre semestre, LocalDate segundaFeira) {
        return criarHorarioVazio(new HorarioSemanal(), semestre, segundaFeira);
    }

    public <T extends HorarioBase> T criarHorarioVazio(T horario, Semestre semestre, LocalDate dataInicioSemana) {
        horario.setSemestre(semestre);

        if(horario instanceof HorarioSemanal semanal) {
            semanal.setSemanaReferencia(dataInicioSemana);
        }

        int aulasPorDia = horario.getSemestre().getSubTurma().getTurma().getQuantidadeAulasPorDia();

        List<AulasDoDia> dias = new ArrayList<>();

        for(DiasDaSemana dia : DiasDaSemana.values()) {
            AulasDoDia diaObjeto = new AulasDoDia();

            diaObjeto.setDiaDaSemana(dia);
            diaObjeto.setHorario(horario);

            List<Aula> aulas = new ArrayList<>();

            for(int i = 0;i < aulasPorDia; i++) {
                Aula aula = new Aula();

                aula.setOrdem(i);
                aula.setAulasDia(diaObjeto);

                aulas.add(aula);
            }
            diaObjeto.setAulas(aulas);
            dias.add(diaObjeto);
        }
        horario.setListaDeAulasDoDia(dias);

        return horario;
    }

    @Transactional
    public void preencherHorario(HorarioBase horario, List<AulasDoDiaDTO> aulasDoDiaDTO) {
        for(int i = 0; i < aulasDoDiaDTO.size(); i++) {
           AulasDoDiaDTO aulasDoDiaDTOAtualizada = aulasDoDiaDTO.get(i);

           AulasDoDia aulasDoDiaExistente = horario.getListaDeAulasDoDia().get(i);

           aulasDoDiaExistente.setDiaDaSemana(aulasDoDiaDTOAtualizada.diaDaSemana());

           List<Aula> aulasExistentes = aulasDoDiaExistente.getAulas();

            for(int j = 0; j < aulasDoDiaDTOAtualizada.aulas().size(); j++) {
                AulaDTO aulaDTO = aulasDoDiaDTOAtualizada.aulas().get(j);

                Aula aulaExistente = aulasExistentes.get(j);

                aulaExistente.setOrdem(j);
                aulaExistente.setAulasDia(aulasDoDiaExistente);

                // Verificações de Campos Preenchidos e atribuição caso preenchidos, caso contrário setados null
                if(aulaDTO.unidadeCurricularId() != null) {
                    unidadeCurricularRepository.findById(aulaDTO.unidadeCurricularId())
                            .ifPresent(aulaExistente::setUnidadeCurricular);
                } else {
                    aulaExistente.setUnidadeCurricular(null);
                }

                if(aulaDTO.professorId() != null) {
                    professorRepository.findById(aulaDTO.professorId())
                            .ifPresent(aulaExistente::setProfessor);
                } else {
                    aulaExistente.setProfessor(null);
                }

                if(aulaDTO.ambienteId() != null) {
                    ambienteRepository.findById(aulaDTO.ambienteId())
                            .ifPresent(aulaExistente::setAmbiente);
                } else {
                    aulaExistente.setAmbiente(null);
                }
            }
        }
    }
}