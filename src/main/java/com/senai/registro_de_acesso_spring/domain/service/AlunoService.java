package com.senai.registro_de_acesso_spring.domain.service;

import com.senai.registro_de_acesso_spring.application.dto.AlunoDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepo;

    public void cadastrar(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();

        aluno.setNome(alunoDTO.nome());
        aluno.setCpf(alunoDTO.cpf());
        aluno.setDataNascimento(alunoDTO.dataNascimento());
        aluno.setEmail(alunoDTO.email());

        alunoRepo.save(aluno);
    }

    public List<AlunoDTO> listar() {
        return alunoRepo.findAll().stream().map(aluno -> new AlunoDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getCpf(),
                aluno.getDataNascimento(),
                aluno.getEmail(),
                aluno.getTelefone()
                )).toList();
    }

    public boolean atualizar(Long Id, AlunoDTO alunoDTO) {
        Optional<Aluno> alunoOpt = alunoRepo.findById(Id);

        if (alunoOpt.isPresent()) {
            Aluno aluno = alunoOpt.get();

            aluno.setNome(alunoDTO.nome());
            aluno.setCpf(alunoDTO.cpf());
            aluno.setTelefone(alunoDTO.telefone());
            aluno.setDataNascimento(alunoDTO.dataNascimento());
            aluno.setEmail(alunoDTO.email());

            alunoRepo.save(aluno);
            return true;
        }
        else return false;
    }

    public Aluno buscarPorId(Long id) {
        Optional<Aluno> alunoOpt = alunoRepo.findById(id);

        if (alunoOpt.isPresent()){
            Aluno aluno = alunoOpt.get();
            return aluno;
        } else return null;
    }   //TODO - Revisar

    public boolean deletar(Long id) {
        return false;
    }   //TODO
}