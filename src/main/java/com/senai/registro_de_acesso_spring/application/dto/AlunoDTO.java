package com.senai.registro_de_acesso_spring.application.dto;

import com.senai.registro_de_acesso_spring.domain.entity.turma.SubTurma;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeUsuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record AlunoDTO(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        String telefone,
        String idAcesso,
        List<Ocorrencia> ocorrencias,
        List<Justificativa> justificativas,
        List<SubTurma> subTurmas
        ) {

        public static AlunoDTO toDTO(Aluno a) {
                List<Ocorrencia> ocorrencias = new ArrayList<>();
                List<Justificativa> justificativas = new ArrayList<>();
                List<SubTurma> subTurmas = new ArrayList<>();
                return new AlunoDTO(a.getId(), a.getNome(), a.getCpf(), a.getDataNascimento(), a.getEmail(),a.getTelefone(), a.getIdAcesso(), ocorrencias, justificativas, subTurmas);
        }

        public Aluno fromDTO() {
                Aluno a = new Aluno();

                a.setId(id);
                a.setNome(nome);
                a.setCpf(cpf);
                a.setTelefone(telefone);
                a.setEmail(email);
                a.setDataNascimento(dataNascimento);
                a.setAtivo(true);
                a.setIdAcesso(idAcesso);
                a.setSubTurmas(subTurmas);
                a.setJustificativas(justificativas);
                a.setOcorrencias(ocorrencias);
                return a;
        }
}
