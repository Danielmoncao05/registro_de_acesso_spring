package com.senai.registro_de_acesso_spring.application.services.usuariosServices;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.AQVDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.AQV;
import com.senai.registro_de_acesso_spring.domain.repositories.usuariosRepositories.AQVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AQVService {
    @Autowired
    private AQVRepository aqvRepository;

    public void cadastrarAQV(AQVDTO dto) {
        aqvRepository.save(dto.fromDTO());
    }

    public List<AQVDTO> listarAQVs() {
        return aqvRepository.findByAtivoTrue().
                stream().map(AQVDTO::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AQVDTO> buscarAQVPorId(Long id) {
        return aqvRepository.findById(id)
                .filter(AQV::isAtivo)
                .map(AQVDTO::toDTO);
    }

    public boolean atualizarAQV(Long id, AQVDTO dto) {
        return aqvRepository.findById(id).map(aqv -> {
            AQV aqvAtualizado = dto.fromDTO();

            aqv.setNome(aqvAtualizado.getNome());
            aqv.setEmail(aqvAtualizado.getEmail());
            aqv.setDataNascimento(aqvAtualizado.getDataNascimento());
            aqv.setCpf(aqvAtualizado.getCpf());

            aqvRepository.save(aqv);
            return true;
        }).orElse(false);
    }

    public boolean inativarAQV(Long id) {
        return aqvRepository.findById(id).map(aqv -> {
            aqv.setAtivo(false);

            aqvRepository.save(aqv);
            return true;
        }).orElse(false);
    }
}