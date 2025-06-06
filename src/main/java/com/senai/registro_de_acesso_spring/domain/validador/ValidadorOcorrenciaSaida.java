package com.senai.registro_de_acesso_spring.domain.validador;

import com.senai.registro_de_acesso_spring.application.dto.OcorrenciaDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Period;

public class ValidadorOcorrenciaSaida {

    public static void validarIdade(OcorrenciaDTO dto, Aluno aluno, Ocorrencia ocorrencia){
        int idade = Period.between(aluno.getDataNascimento(), LocalDate.now()).getYears();
        if (idade < 18) {
            throw new IllegalArgumentException("O aluno menor de idade necessita de uma autorização de um responsável.");
        } else {
            ocorrencia.setDescricao(dto.descricao());
        }
    }
}
