package com.senai.registro_de_acesso_spring.application.service.usuariosServices.alunoServices;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.JustificativaDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.registro_de_acesso_spring.domain.enums.StatusDaJustificativa;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeJustificativa;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.alunoRepositories.JustificativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JustificativaService {
    @Autowired
    private JustificativaRepository justificativaRepository;

    // CRUD de Justificativa
    public void registrarJustificativa(JustificativaDTO dto) {
        justificativaRepository.save(dto.fromDTO());
    }

    public Optional<JustificativaDTO> buscarJustificativaPorId(Long id) {
        return justificativaRepository.findById(id)
                // .filter(justificativa -> justificativa.getStatus() == StatusDaJustificativa.APROVADO)
                .map(JustificativaDTO::toDTO);
    }

    public List<JustificativaDTO> listarJustificativas() {
        return justificativaRepository.findAll() // justificativaRepository.findByStatusDaJustificativa(StatusDaJustificativa.APROVADO)
                .stream().map(JustificativaDTO::toDTO).toList();
    }

    public boolean atualizarJustificativa(Long id, JustificativaDTO dto) {
        return justificativaRepository.findById(id).map(justificativa -> {
            Justificativa justificativaAtualizada = dto.fromDTO();

            justificativa.setTipo(justificativaAtualizada.getTipo());
            justificativa.setDescricao(justificativaAtualizada.getDescricao());
            justificativa.setAnexo(justificativaAtualizada.getAnexo());
            justificativa.setDataInicial(justificativaAtualizada.getDataInicial());
            justificativa.setQuantidadeDias(justificativaAtualizada.getQuantidadeDias());
            justificativa.setAluno(justificativaAtualizada.getAluno());

            justificativaRepository.save(justificativa);
            return true;
        }).orElse(false);
    }

    public boolean deletarJustificativa(Long id) {
        if(justificativaRepository.existsById(id)) {
            justificativaRepository.deleteById(id);
            return true;
        }else  {
            return false;
        }
        /*return justificativaRepository.findById(id).map(justificativa -> {
            justificativa.setStatus(StatusDaJustificativa.REPROVADO);

            justificativaRepository.save(justificativa);
            return true;
        }).orElse(false);*/
    }

    // CRUD de Justificativa de Falta
    public void criarJustificativaFalta(JustificativaDTO dto) {
        Justificativa justificativa = dto.fromDTO();

        // Instanciando justificativa com Tipo fixo = FALTA e Status = AGUARDANDO_ANALISE
        justificativa.setTipo(TipoDeJustificativa.FALTA);
        justificativa.setStatus(StatusDaJustificativa.AGUARDANDO_ANALISE);

        justificativaRepository.save(justificativa);
    }

    public Optional<JustificativaDTO> listarJustificativaFaltaPorId(Long id) {
        return justificativaRepository.findById(id)
                .filter(justificativa -> justificativa.getTipo() == TipoDeJustificativa.FALTA)
                .map(JustificativaDTO::toDTO);
    }

    public List<JustificativaDTO> listarJustificativasFalta() {
        return justificativaRepository.findAll()
                .stream().filter(justificativa -> justificativa.getTipo() == TipoDeJustificativa.FALTA)
                .map(JustificativaDTO::toDTO)
                .collect(Collectors.toList());
    }

    public boolean alterarJustificativaFalta(Long id, JustificativaDTO dto) {
        return justificativaRepository.findById(id).filter(justificativa -> justificativa.getTipo() == TipoDeJustificativa.FALTA)
                .map(justificativa -> {
                    Justificativa justificativaAlterada = dto.fromDTO();

                    /*// NÃO alterar o tipo e nem o aluno
                    justificativa.setTipo(TipoDeJustificativa.FALTA);
                    justificativa.setAluno(justificativaAlterada.getAluno());*/

                    justificativa.setDescricao(justificativaAlterada.getDescricao());
                    justificativa.setAnexo(justificativaAlterada.getAnexo());
                    justificativa.setDataInicial(justificativaAlterada.getDataInicial());
                    justificativa.setQuantidadeDias(justificativaAlterada.getQuantidadeDias());
                    justificativa.setStatus(StatusDaJustificativa.AGUARDANDO_ANALISE); // ao alterar os dados, o status volta a aguardando para nova analise

                    justificativaRepository.save(justificativa);
                    return true;
                }).orElse(false);
    }

    public boolean deletarJustificativaFalta(Long id) {
        if(justificativaRepository.findById(id).filter(justificativa -> justificativa.getTipo() == TipoDeJustificativa.FALTA).isPresent()) {
            justificativaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}