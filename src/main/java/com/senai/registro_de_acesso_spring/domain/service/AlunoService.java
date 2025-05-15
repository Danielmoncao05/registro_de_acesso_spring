package com.senai.registro_de_acesso_spring.domain.service;

import com.senai.registro_de_acesso_spring.application.dto.AlunoDTO;
import com.senai.registro_de_acesso_spring.application.dto.UsuarioDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Usuario;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepo;

    public void cadastrar(AlunoDTO alunoDTO) {
        alunoRepo.save(alunoDTO.fromDTO());
    }

    public List<AlunoDTO> listar() {
        System.out.println("alunos sendo listados");
        return alunoRepo.findByAtivoTrue()
                .stream().map(AlunoDTO::toDTO)
                .collect(Collectors.toList());
    }

    public boolean atualizar(Long id, AlunoDTO alunoDTO) {
        return alunoRepo.findById(id).map(aluno -> {
            Aluno alunoAtualizado = alunoDTO.fromDTO();
            aluno.setNome(alunoAtualizado.getNome());
            aluno.setEmail(alunoAtualizado.getEmail());
            aluno.setDataNascimento(alunoAtualizado.getDataNascimento());
            aluno.setCpf(alunoAtualizado.getCpf());
            aluno.setSubTurmas(alunoAtualizado.getSubTurmas());
            aluno.setOcorrencias(alunoAtualizado.getOcorrencias());
            aluno.setJustificativas(alunoAtualizado.getJustificativas());
            aluno.setTelefone(alunoAtualizado.getTelefone());
            alunoRepo.save(aluno);
            return true;
        }).orElse(false);
    }

    public Optional<AlunoDTO> buscarPorId(Long id) {
        return alunoRepo.findById(id)
                .filter(Aluno::isAtivo)
                .map(AlunoDTO::toDTO);
    }

    public boolean desativar(Long id) {
        return alunoRepo.findById(id).map(aluno -> {
            aluno.setAtivo(false);
            alunoRepo.save(aluno);
            return true;
        }).orElse(false);
    }
}