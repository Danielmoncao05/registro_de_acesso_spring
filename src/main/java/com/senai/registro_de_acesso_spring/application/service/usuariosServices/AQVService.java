package com.senai.registro_de_acesso_spring.application.service.usuariosServices;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.AQVDTO;
import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.JustificativaDTO;
import com.senai.registro_de_acesso_spring.application.service.usuariosServices.alunoServices.JustificativaService;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.AQV;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.registro_de_acesso_spring.domain.enums.StatusDaJustificativa;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeJustifcativa;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.AQVRepository;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.alunoRepositories.JustificativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AQVService {
    @Autowired
    private AQVRepository aqvRepository;

    @Autowired
    private JustificativaRepository justificativaRepository;

    @Autowired
    JustificativaService justificativaService;

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
            aqv.setCpf(aqvAtualizado.getCpf());
            aqv.setDataNascimento(aqvAtualizado.getDataNascimento());
            aqv.setEmail(aqvAtualizado.getEmail());
            aqv.setTelefone(aqvAtualizado.getTelefone());

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

    // AQV alterar Status de Justificativa de Saída Antecipada
    public Optional<Boolean> alterarStatusJustificativaSaida(Long idJustificativa, JustificativaDTO dto) {
        return justificativaRepository.findByIdAndTipoAndStatus(idJustificativa, TipoDeJustifcativa.SAIDA_ANTECIPADA, StatusDaJustificativa.AGUARDANDO_ANALISE)
                .map(justificativa -> {
                            Justificativa justificativaAlterada = dto.fromDTO();

                            if(justificativaAlterada.getStatus().equals(StatusDaJustificativa.APROVADO)) {
                                justificativa.setStatus(justificativaAlterada.getStatus());

                                justificativaRepository.save(justificativa);
                                return true;
                            } else if(justificativaAlterada.getStatus().equals(StatusDaJustificativa.REPROVADO)) {
                                justificativa.setStatus(justificativaAlterada.getStatus());

                                justificativaRepository.save(justificativa);
                                return true;
                            }
                            return false;
                        }
                );
    }
}