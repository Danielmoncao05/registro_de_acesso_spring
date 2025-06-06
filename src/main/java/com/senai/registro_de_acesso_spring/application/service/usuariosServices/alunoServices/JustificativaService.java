package com.senai.registro_de_acesso_spring.application.service.usuariosServices.alunoServices;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.JustificativaDTO;
import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.OcorrenciaDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.registro_de_acesso_spring.domain.enums.StatusDaJustificativa;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeJustifcativa;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.alunoRepositories.JustificativaRepository;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.alunoRepositories.OcorrenciaRepository;
import com.senai.registro_de_acesso_spring.domain.service.JustificativasRegras.JustificativaServiceRegras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JustificativaService {
    @Autowired
    private JustificativaRepository justificativaRepository;

    @Autowired
    private JustificativaServiceRegras justificativaServiceRegras;

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

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
        } /*return justificativaRepository.findById(id).map(justificativa -> {
            justificativa.setStatus(StatusDaJustificativa.REPROVADO);

            justificativaRepository.save(justificativa);
            return true;
        }).orElse(false);*/
    }


    // topico para justificativas de saidas antecipadas

    public void justificarSaidaAntecipada (JustificativaDTO justificativaDTO , Long idOcorrencia) {

        Ocorrencia ocorrencia = justificativaServiceRegras.validarOcorrencia(idOcorrencia);

        Aluno aluno = ocorrencia.getAluno();

        Justificativa justificativa = justificativaDTO.fromDTO();
        justificativa.setTipo(TipoDeJustifcativa.SAIDA_ANTECIPADA);
        justificativa.setDescricao(justificativaDTO.descricao());
        justificativa.setAnexo(justificativaDTO.anexo());
        justificativa.setDataInicial(LocalDate.now());
        justificativa.setQuantidadeDias(justificativaDTO.quantidadeDias());
        justificativa.setAluno(aluno);
        justificativa.setOcorrencia(ocorrencia);

        justificativaRepository.save(justificativa);

//        if (ocorrenciaRepository.findBy(ocorrencia))
//        Justificativa justificativa = justificativaDTO.fromDTO();
//        justificativa.setStatus(StatusDaJustificativa.AGUARDANDO_ANALISE);
    }

    public List<JustificativaDTO> listarJustificativasSaidas() {
        return justificativaRepository.findAll().stream().map(JustificativaDTO::toDTO).collect(Collectors.toList());
    }
}