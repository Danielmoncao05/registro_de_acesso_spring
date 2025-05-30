package com.senai.registro_de_acesso_spring.domain.service.OcorrenciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class OcorrenciaServiceDomain {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public boolean verificarAtraso(LocalTime horaEntrada, Integer tolerancia){
        LocalTime horarioLimite = horaEntrada.plusMinutes(tolerancia);
        return LocalTime.now().isAfter(horarioLimite);
    }

    /*@Transactional
    public void solicitarSaidaAntecipada(OcorrenciaDTO dto) {
        Aluno aluno = alunoRepository.findById(dto.alunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Ocorrencia ocorrencia = dto.fromDTO();

        ocorrencia.setStatus(StatusDaOcorrencia.AGUARDANDO_AUTORIZACAO);
        ocorrencia.setDataHoraCriacao(LocalDateTime.now());
        ocorrencia.setAluno(aluno);
        ocorrencia.setTipo(TipoDeOcorrencia.SAIDA_ANTECIPADA);

        Ocorrencia saved = ocorrenciaRepository.save(ocorrencia);

        messagingTemplate.convertAndSend(
                "/topic/aqv",
                OcorrenciaDTO.toDTO(saved)
        );
    }

    public void decidirSaida(OcorrenciaDTO dto) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));

        if (dto.status() == StatusDaOcorrencia.REPROVADO) {
            ocorrencia.setStatus(StatusDaOcorrencia.REPROVADO);
            ocorrenciaRepository.save(ocorrencia);
            messagingTemplate.convertAndSend(
                    "/topic/aluno/" +
                            ocorrencia.getAluno().getId(),
                    OcorrenciaDTO.toDTO(ocorrencia)
            );
            return;
        }

        ocorrencia.setStatus(StatusDaOcorrencia.AGUARDANDO_CIENCIA_PROFESSOR);

        Professor professor = professorRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        ocorrencia.setProfessorResponsavel(professor);
        Ocorrencia saved = ocorrenciaRepository.save(ocorrencia);

        messagingTemplate.convertAndSend(
                "/topic/professor/" +
                        professor.getId(),
                OcorrenciaDTO.toDTO(saved)
        );
    }

    public void confirmarCiencia(OcorrenciaDTO dto) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));

        ocorrencia.setStatus(StatusDaOcorrencia.APROVADO);
        Ocorrencia saved = ocorrenciaRepository.save(ocorrencia);

        messagingTemplate.convertAndSend(
                "/topic/aluno/" +
                        ocorrencia.getAluno().getId(),
                OcorrenciaDTO.toDTO(saved)
        );
    }*/
}