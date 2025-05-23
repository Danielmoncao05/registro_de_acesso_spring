package com.senai.registro_de_acesso_spring.application.service.usuariosServices;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.CoordenadorDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Coordenador;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.CoordenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoordenadorService {
    @Autowired
    private CoordenadorRepository coordenadorRepository;

    public void cadastrarCoordenador(CoordenadorDTO dto) {
        coordenadorRepository.save(dto.fromDTO());
    }

    public List<CoordenadorDTO> listarCoordenadores() {
        return coordenadorRepository.findByAtivoTrue()
                .stream().map(CoordenadorDTO::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CoordenadorDTO> buscarCoordenadorPorId(Long id) {
        return coordenadorRepository.findById(id)
                .filter(Coordenador::isAtivo)
                .map(CoordenadorDTO::toDTO);
    }

    public boolean atualizarCoordenador(Long id, CoordenadorDTO dto) {
        return coordenadorRepository.findById(id).map(coordenador -> {
            Coordenador coordenadorAtualizado = dto.fromDTO();

            coordenador.setNome(coordenadorAtualizado.getNome());
            coordenador.setEmail(coordenadorAtualizado.getEmail());
            coordenador.setSenha(coordenadorAtualizado.getSenha());
            coordenador.setDataNascimento(coordenadorAtualizado.getDataNascimento());
            coordenador.setCpf(coordenadorAtualizado.getCpf());
            coordenador.setTelefone(coordenadorAtualizado.getTelefone());

            coordenadorRepository.save(coordenador);
            return true;
        }).orElse(false);
    }

    public boolean inativarCoordenador(Long id) {
        return coordenadorRepository.findById(id).map(coordenador -> {
            coordenador.setAtivo(false);

            coordenadorRepository.save(coordenador);
            return true;
        }).orElse(false);
    }

}