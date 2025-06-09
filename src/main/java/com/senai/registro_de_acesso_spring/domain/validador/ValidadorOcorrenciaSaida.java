package com.senai.registro_de_acesso_spring.domain.validador;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;

import java.time.LocalDate;
import java.time.Period;

public class ValidadorOcorrenciaSaida {

    public static void validarIdade( Aluno aluno){
        int idade = Period.between(aluno.getDataNascimento(), LocalDate.now()).getYears();
        if (idade < 18) {
            throw new IllegalArgumentException("O aluno " + aluno.getNome() + " menor de idade, necessita de uma autorização de um responsável.");
        } else {
            System.out.println( aluno.getNome() +" está autorizado para solicitar saída.");
        }
    }
}
