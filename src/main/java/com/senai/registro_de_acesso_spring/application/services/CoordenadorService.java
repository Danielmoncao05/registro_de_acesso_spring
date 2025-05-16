package com.senai.registro_de_acesso_spring.application.services;

import com.senai.registro_de_acesso_spring.application.dto.AlunoDTO;
import com.senai.registro_de_acesso_spring.application.dto.CoordenadorDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Coordenador;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.repositories.CoordenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoordenadorService {
    @Autowired
    private CoordenadorRepository coordenadorRepo;

    public void cadastrar(CoordenadorDTO coordenadorDTO) {
        coordenadorRepo.save(coordenadorDTO.fromDTO());
    }

    public List<CoordenadorDTO> listar() {
        return coordenadorRepo.findByAtivoTrue()
                .stream().map(CoordenadorDTO::toDTO)
                .collect(Collectors.toList());
    }

    public boolean atualizar(Long id, CoordenadorDTO coordenadorDTO) {
        return coordenadorRepo.findById(id).map(coordenador -> {
            Coordenador coordenadorAtualizado = coordenadorDTO.fromDTO();
            coordenador.setNome(coordenadorAtualizado.getNome());
            coordenador.setEmail(coordenadorAtualizado.getEmail());
            coordenador.setDataNascimento(coordenadorAtualizado.getDataNascimento());
            coordenador.setCpf(coordenadorAtualizado.getCpf());
            coordenador.setTelefone(coordenadorAtualizado.getTelefone());
            coordenadorRepo.save(coordenador);
            return true;
        }).orElse(false);
    }

    public Optional<CoordenadorDTO> buscarPorId(Long id) {
        return coordenadorRepo.findById(id)
                .filter(Coordenador::isAtivo)
                .map(CoordenadorDTO::toDTO);
    }

    public boolean desativar(Long id) {
        return coordenadorRepo.findById(id).map(coordenador -> {
            coordenador.setAtivo(false);
            coordenadorRepo.save(coordenador);
            return true;
        }).orElse(false);
    }
}
