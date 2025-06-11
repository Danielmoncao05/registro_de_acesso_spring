package com.senai.registro_de_acesso_spring.application.service.usuariosServices;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.CoordenadorDTO;
import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.JustificativaDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Coordenador;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.registro_de_acesso_spring.domain.enums.StatusDaJustificativa;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeJustificativa;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.CoordenadorRepository;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.alunoRepositories.JustificativaRepository;
import com.sun.jdi.PrimitiveValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoordenadorService {
    @Autowired
    private CoordenadorRepository coordenadorRepository;

    @Autowired
    private JustificativaRepository justificativaRepository;

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

    // Coordenador alterar Status de Justificativa de Falta
    public Optional<Boolean> alterarStatusJustificativaFalta(Long idJustificativa, JustificativaDTO dto) {
        return justificativaRepository.findById(idJustificativa).filter(justificativa -> justificativa.getTipo() == TipoDeJustificativa.FALTA)
                .filter(justificativa -> justificativa.getStatus() == StatusDaJustificativa.AGUARDANDO_ANALISE).map(justificativa -> {
            Justificativa justificativaStatusAlterado = dto.fromDTO();

            if(justificativaStatusAlterado.getStatus().equals(StatusDaJustificativa.APROVADO)) {
                justificativa.setStatus(justificativaStatusAlterado.getStatus());

                justificativaRepository.save(justificativa);
                return true;
            } else if(justificativaStatusAlterado.getStatus().equals(StatusDaJustificativa.REPROVADO)) {
                justificativa.setStatus(justificativaStatusAlterado.getStatus());

                justificativaRepository.save(justificativa);
                return true;
            }
            return false;
        });
    }

    // Coordenador alterar Status de Justificativa de Atraso
    public Optional<Boolean> alterarStatusJustificativaAtraso(Long idJustificativa, JustificativaDTO dto) {
        return justificativaRepository.findByIdAndTipo(idJustificativa, TipoDeJustificativa.ATRASO)
                .filter(justificativa -> justificativa.getStatus() == StatusDaJustificativa.AGUARDANDO_ANALISE).map(justificativa -> {
            Justificativa justificativaStatusAlterado = dto.fromDTO();

            if(justificativaStatusAlterado.getStatus().equals(StatusDaJustificativa.APROVADO)) {
                justificativa.setStatus(justificativaStatusAlterado.getStatus());

                justificativaRepository.save(justificativa);
                return true;
            } else if(justificativaStatusAlterado.getStatus().equals(StatusDaJustificativa.REPROVADO)) {
                justificativa.setStatus(justificativaStatusAlterado.getStatus());

                justificativaRepository.save(justificativa);
                return true;
            }
            return false;
        });
    }

    // Coordenador alterar Status de Justificativa de Saída Antecipada
    public Optional<Boolean> alterarStatusJustificativaSaida(Long idJustificativa, JustificativaDTO dto) {
        return justificativaRepository.findByIdAndTipoAndStatus(idJustificativa, TipoDeJustificativa.SAIDA_ANTECIPADA, StatusDaJustificativa.AGUARDANDO_ANALISE)
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