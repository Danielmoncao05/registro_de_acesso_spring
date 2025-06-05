package com.senai.registro_de_acesso_spring.application.dto;

import com.senai.registro_de_acesso_spring.domain.entity.curso.UnidadeCurricular;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.enuns.StatusDaOcorrencia;
import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeOcorrencia;

import java.time.LocalDateTime;


public record OcorrenciaDTO(Long id,
                            Aluno aluno,
                            Professor professorResponsavel,
                            UnidadeCurricular unidadeCurricular,
                            TipoDeOcorrencia tipoDeOcorrencia,
                            String descricao,
                            StatusDaOcorrencia statusDaOcorrencia,
                            LocalDateTime dataHoraConclusao,
                            LocalDateTime PedidoDeSaida) {



    //PUXAR O DIA AUTOMATICO
}
