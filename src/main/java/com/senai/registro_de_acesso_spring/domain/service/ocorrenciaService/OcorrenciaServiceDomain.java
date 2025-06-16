package com.senai.registro_de_acesso_spring.domain.service.ocorrenciaService;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.enums.DiasDaSemana;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class OcorrenciaServiceDomain {
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar Ocorrencia de Atraso com MQTT
    public boolean criarOcorrenicaDeAtraso(String idAcesso) {
        usuarioRepository.findByIdAcesso(idAcesso).map(usuario -> {
            if (usuario instanceof Aluno aluno) {
                System.out.println("È Aluno");
                verificarAtraso(aluno, LocalDateTime.now());
                return true;
            } else {
                System.out.println("Não é Aluno");
                return  false;
            }
        });
        return false;
    }

    // atraso, aula, professor
    public boolean verificarAtraso(Aluno aluno, LocalDateTime entradaAluno) {
        LocalTime horarioEntradaAluno = entradaAluno.toLocalTime();
        LocalTime horarioEntradaTurma = aluno.getSubTurma().getTurma().getHorarioEntrada();
        Integer tolerancia = aluno.getSubTurma().getTurma().getCurso().getToleranciaMinutos();

        return horarioEntradaAluno.isAfter((horarioEntradaTurma).plusMinutes(tolerancia));
    }

    public Enum<DiasDaSemana> diaSemana(DayOfWeek semanaIngles) {
        switch(semanaIngles) {
            case MONDAY -> {
                return DiasDaSemana.SEGUNDA;
            }
            case TUESDAY -> {
                return DiasDaSemana.TERCA;
            }
            case WEDNESDAY -> {
                return DiasDaSemana.QUARTA;
            }
            case THURSDAY -> {
                return DiasDaSemana.QUINTA;
            }
            case FRIDAY -> {
                return DiasDaSemana.SEXTA;
            }
            default -> {
               return null;
            }
        }
    }
}