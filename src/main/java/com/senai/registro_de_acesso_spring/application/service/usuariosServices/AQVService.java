package com.senai.registro_de_acesso_spring.application.service.usuariosServices;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.AQVDTO;
import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.JustificativaDTO;
import com.senai.registro_de_acesso_spring.application.service.usuariosServices.alunoServices.JustificativaService;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.AQV;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.registro_de_acesso_spring.domain.enums.StatusDaJustificativa;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeJustificativa;
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
    private JustificativaService justificativaService;

    // CRUD de AQV
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

    // TODO criar metodo unico para nao repetir codigo

    // TODO pensar em um jeito de utilizar o dto.id e nao afetar o controller,nem o funcionamento

    // AQV alterar Status de Justificativa de Falta
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
                }
        );
    }

    // AQV alterar Status de Justificativa de Atraso
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
                }
        );
    }

    // AQV alterar Status de Justificativa de Saída Antecipada
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

    /*public boolean alterarStatusJustificativaFalta(Long idJustificativa, JustificativaDTO dto) {
        if(justificativaRepository.findById(dto.id()).filter(justificativa -> justificativa.getTipo() == TipoDeJustificativa.FALTA).isPresent()) {
            Justificativa justificativa = dto.fromDTO();

            System.out.println("Justificativa: " + justificativa + ", tipo: " + justificativa.getTipo() + ", status: " + justificativa.getStatus());
            if(dto.status().equals(StatusDaJustificativa.APROVADO)) {
                justificativa.setStatus(StatusDaJustificativa.APROVADO);

                System.out.println("Justificativa Status: " + justificativa.getStatus() + " - aprovado?");
                return true;
            } else if(dto.status().equals(StatusDaJustificativa.REPROVADO)) {
                justificativa.setStatus(StatusDaJustificativa.REPROVADO);

                System.out.println("Justificativa Status: " + justificativa.getStatus() + " - reprovado?");
                return true;
            }
            // guardar a variavel e utiliza-la ao inves de procurar no banco duas vezes
            /*justificativaRepository.findById(dto.id()).filter(justificativa -> justificativa.getStatus() == StatusDaJustificativa.AGUARDANDO_ANALISE)
                    .map(justificativa -> { // pensar num modo de aprovar e reprovar utilizando if
                        if(dto.status().equals(StatusDaJustificativa.APROVADO)) {
                            justificativa.setStatus(StatusDaJustificativa.APROVADO); // justificativa.setStatus(StatusDaJustificativa.APROVADO);
                            return true;
                        } else if(dto.status().equals(StatusDaJustificativa.REPROVADO)) {
                            justificativa.setStatus(StatusDaJustificativa.REPROVADO);
                            return true;
                        }
                        return false;
                    });
    //  if(justificativaRepository.findById(idJustificativa).filter(justificativa -> justificativa.getStatus() == StatusDaJustificativa.AGUARDANDO_ANALISE).isPresent()) {}
}
        System.out.println("vazio");
        return false;
                }*/
}