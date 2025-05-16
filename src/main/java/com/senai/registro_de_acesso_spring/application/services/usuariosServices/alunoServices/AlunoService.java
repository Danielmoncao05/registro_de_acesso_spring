package com.senai.registro_de_acesso_spring.application.services.usuariosServices.alunoServices;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.AlunoDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.repositories.usuariosRepositories.alunoRepositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public void cadastrarAluno(AlunoDTO dto) {
        alunoRepository.save(dto.fromDTO()); // nao é necessario a foto do aluno para cadastrar e as ocorerncias serao criada quando necessario apenas
    }

    public List<AlunoDTO> listarAlunos() {
        return alunoRepository.findByAtivoTrue()
                .stream().map(AlunoDTO::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AlunoDTO> buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id)
                .filter(Aluno::isAtivo)
                .map(AlunoDTO::toDTO);
    }

    public boolean atualizarAluno(Long id, AlunoDTO dto) {
        return alunoRepository.findById(id).map(aluno -> {
            Aluno alunoAtualizado = dto.fromDTO();

            aluno.setNome(alunoAtualizado.getNome());
            aluno.setCpf(alunoAtualizado.getCpf());
            aluno.setDataNascimento(alunoAtualizado.getDataNascimento());
            aluno.setEmail(alunoAtualizado.getEmail());
            aluno.setTelefone(alunoAtualizado.getTelefone());
            /*
            aluno.setIdAcesso(alunoAtualizado.getIdAcesso());
            aluno.setSenha(alunoAtualizado.getSenha());
            aluno.setFoto(alunoAtualizado.getFoto());
            */
            aluno.setJustificativas(alunoAtualizado.getJustificativas());
            aluno.setOcorrencias(alunoAtualizado.getOcorrencias());
            aluno.setSubTurmas(alunoAtualizado.getSubTurmas());

            alunoRepository.save(aluno);
            return true;
        }).orElse(false);
    }

    public boolean inativarAluno(Long id) {
        return alunoRepository.findById(id).map(aluno -> {
            aluno.setAtivo(false);

            alunoRepository.save(aluno);
            return true;
        }).orElse(false);
    }

}