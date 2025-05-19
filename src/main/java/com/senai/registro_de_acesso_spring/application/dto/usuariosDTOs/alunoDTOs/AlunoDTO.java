package com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs;

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
        String idAcesso,
        String email,
        String telefone,
        TipoDeUsuario tipo,
        List<Ocorrencia> ocorrencias,
        List<Justificativa> justificativas,
        List<SubTurma> subTurmas
) {
    public static AlunoDTO toDTO(Aluno a) {
        TipoDeUsuario tipo = TipoDeUsuario.ALUNO;
        return new AlunoDTO(a.getId(), a.getNome(), a.getCpf(), a.getDataNascimento(), a.getIdAcesso(), a.getEmail(), a.getTelefone(), tipo, a.getOcorrencias(), a.getJustificativas(), a.getSubTurmas());
    }

    public Aluno fromDTO() {
        Aluno aluno = new Aluno();

        aluno.setId(id);
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setEmail(email);
        aluno.setDataNascimento(dataNascimento);
        aluno.setTelefone(telefone);
        aluno.setIdAcesso(idAcesso);
        aluno.setAtivo(true);
        aluno.setSenha("");
        aluno.setSubTurmas(subTurmas);
        aluno.setJustificativas(justificativas);
        aluno.setOcorrencias(ocorrencias);

        return aluno;
    }
}