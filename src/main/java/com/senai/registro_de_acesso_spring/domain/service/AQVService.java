package com.senai.registro_de_acesso_spring.domain.service;

import com.senai.registro_de_acesso_spring.application.dto.AQVDto;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.AQV;
import com.senai.registro_de_acesso_spring.domain.repositories.AQVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AQVService {

    @Autowired
    public AQVRepository aqvRepo;

    public void cadastrarAQV (AQVDto aqvDto) {
        aqvRepo.save(aqvDto.fromDTO());
    }

    public Optional<AQVDto> buscarPorId (Long id) {
        return aqvRepo.findById(id).map(AQVDto::toDTO);
    }

    public List<AQVDto> listar() {

        return aqvRepo.findAll().stream().map(AQVDto::toDTO).toList();

    }

    public boolean atualizar(Long id, AQVDto aqvDto) {
        return aqvRepo.findById(id).map(aqv -> {
            AQV aqvAtualizado = aqvDto.fromDTO();
            aqv.setNome(aqvAtualizado.getNome());
            aqv.setCpf(aqvAtualizado.getCpf());
            aqv.setDataNascimento(aqvAtualizado.getDataNascimento());
            aqv.setEmail(aqvAtualizado. getEmail());
            aqvRepo.save(aqv);
            return true;
        }).orElse(false);
    }

    public boolean deletarAqv( Long id) {
        if (aqvRepo.existsById(id)) {
            aqvRepo.deleteById(id);
            return true;
        } else  {
            return false;
        }
    }
}
