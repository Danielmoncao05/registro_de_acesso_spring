package com.senai.registro_de_acesso_spring.domain.service;


import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.AlunoDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeJustifcativa;
import com.senai.registro_de_acesso_spring.domain.repositories.usuariosRepositories.UsuarioRepository;
import com.senai.registro_de_acesso_spring.domain.repositories.usuariosRepositories.alunoRepositories.JustificativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class ValidadorJustificativaAtraso {
    @Autowired
    private JustificativaRepository justificativaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /*public void ada(AlunoDTO dto, TipoDeJustifcativa tipo) {
        if(tipo.equals(TipoDeJustifcativa.ATRASO)) {
            criarJustificativaAtraso(dto);
        } else if(tipo.equals(TipoDeJustifcativa.FALTA)) {
            criarJustificativaFalta(dto);
        }
    }*/

    public void criarJustificativaAtraso(AlunoDTO dto) {
        // OBS: fiz de dois jeitos, ambos ainda não testados.
        if(verificarAluno(dto.idAcesso())) {
            String horarioAula = "13:25:00";
            String horarioAluno = "13:37:32";


            if(!horarioAluno.equals(horarioAula)) {
                System.out.println("Atrasado fiote");
            } else {
                System.out.println("Pontual menó");
            }
        }

        if(verificarAluno(dto.idAcesso())) {
            dto.subTurmas().stream().map(subTurma -> {
                try {
                    // LocalTime.from() transfroma em localTime
                    LocalTime horarioAluno = LocalTime.from(subTurma.getTurma().getHorarioEntrada());
                    LocalTime horarioAula = LocalTime.from(dto.subTurmas().get(0).getTurma().getHorarioEntrada());

                    // isAfter e isBefore realiza a comparacao de tempo dos LocalTime
                    if(horarioAluno.isAfter(horarioAula)) {
                        System.out.println("Chegou tarde filhão, rala peito e aguarda autorização");
                    } else if(horarioAluno.isBefore(horarioAula)) {
                        System.out.println("Boa mlk chegou cedo");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return null;
            });
        }
    }

    private boolean verificarAluno(String idAcesso) {
        usuarioRepository.findByIdAcesso(idAcesso).map(usuario -> {
            if(usuario instanceof Aluno) {
                System.out.println("Aluno");
                return true;
            } else {
                System.out.println("Não é Aluno");
                return false;
            }
        });
        return false;
    }
}