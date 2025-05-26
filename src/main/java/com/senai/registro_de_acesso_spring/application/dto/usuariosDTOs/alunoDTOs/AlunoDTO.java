package com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs;

import com.senai.registro_de_acesso_spring.domain.entity.turma.SubTurma;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeUsuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record AlunoDTO(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        String senha,
        String telefone,
        String idAcesso,
        TipoDeUsuario tipo,
        List<Ocorrencia> ocorrencias,
        List<Justificativa> justificativas,
        SubTurma subTurma
) {
    public static AlunoDTO toDTO(Aluno a) {
        TipoDeUsuario tipo = TipoDeUsuario.ALUNO;
        return new AlunoDTO(
                a.getId(),
                a.getNome(),
                a.getCpf(),
                a.getDataNascimento(),
                a.getIdAcesso(),
                a.getEmail(),
                a.getSenha(),
                a.getTelefone(),
                tipo,
                a.getOcorrencias(),
                a.getJustificativas(),
                a.getSubTurma()
        );
    }

    public Aluno fromDTO() {
        Aluno a = new Aluno();

        a.setId(id);
        a.setNome(nome);
        a.setCpf(cpf);
        a.setEmail(email);
        a.setDataNascimento(dataNascimento);
        a.setTelefone(telefone);
        a.setIdAcesso(idAcesso);
        a.setAtivo(true);
        a.setSenha(senha);
        a.setSubTurma(subTurma);
        a.setJustificativas(new ArrayList<>());
        a.setOcorrencias(new ArrayList<>());

        return a;
    }
}