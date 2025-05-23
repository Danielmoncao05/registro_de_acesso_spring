package com.senai.registro_de_acesso_spring.application.dto;

import com.senai.registro_de_acesso_spring.domain.entity.curso.UnidadeCurricular;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.enuns.StatusDaOcorrencia;
import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeOcorrencia;

import java.time.LocalDateTime;
import java.time.LocalTime;


public record OcorrenciaSaidaDTO(Long id,
                                 TipoDeOcorrencia tipoDeOcorrencia,
                                 String descricao,
                                 //Aluno aluno,
                                 StatusDaOcorrencia statusDaOcorrencia,
                                 //Professor professorResponsavel,
                                 //UnidadeCurricular unidadeCurricular,
                                 LocalDateTime horaDeSaida,
                                 LocalDateTime horaPedido){//PUXAR O DIA AUTOMATICO
}
