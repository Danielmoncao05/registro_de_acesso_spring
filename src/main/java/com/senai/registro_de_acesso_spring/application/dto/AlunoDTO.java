package com.senai.registro_de_acesso_spring.application.dto;

import com.senai.registro_de_acesso_spring.domain.entity.turma.SubTurma;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;

import java.time.LocalDate;
import java.util.List;

public record AlunoDTO(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        String telefone,
        List<Ocorrencia> ocorrencias,
        List<Justificativa> justificativas,
        List<SubTurma> subTurmas
        ) {

        public static AlunoDTO toDTO(Aluno a) {
                return new AlunoDTO(a.getId(), a.getNome(), a.getCpf(), a.getDataNascimento(), a.getEmail(), a.getTelefone(), a.getOcorrencias(), a.getJustificativas(), a.getSubTurmas());
        }

        public Aluno fromDTO() {
                Aluno a = new Aluno();
                a.setId(id);
                a.setNome(nome);
                a.setCpf(cpf);
                a.setDataNascimento(dataNascimento);
                a.setEmail(email);
                a.setTelefone(telefone);
                a.setOcorrencias(ocorrencias);
                a.setJustificativas(justificativas);
                a.setSubTurmas(subTurmas);
                return a;
        }
}
