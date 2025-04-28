package com.senai.registro_de_acesso_spring.domain.service;

import com.senai.registro_de_acesso_spring.domain.entities_BAGUNCADO.Aluno;
import com.senai.registro_de_acesso_spring.domain.repositories.AlunoRepository;
import com.senai.registro_de_acesso_spring.presentation.dtos.AlunoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepo;

    public void cadastrar(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();

        aluno.setNome(alunoDTO.nome());
        aluno.setTelefone(alunoDTO.telefone());
        aluno.setEmail(alunoDTO.email());
        /*
         nao é necessario a foto do aluno para cadastrar e as ocorerncias serao criada quando necessario apenas */

        alunoRepo.save(aluno);
    }

    public List<AlunoDTO> listarAlunos() {
        return alunoRepo.findAll().stream().map(aluno -> new AlunoDTO(
                aluno.getId(),
                aluno.getIdDeAcesso(),
                aluno.getNome(),
                aluno.getTelefone(),
                aluno.getEmail(),
                aluno.getFoto(),
                aluno.getOcorrencia(),
                aluno.getJustificativa()
        )).toList(); // pegar tudo?? | como pegar separado
    }

}