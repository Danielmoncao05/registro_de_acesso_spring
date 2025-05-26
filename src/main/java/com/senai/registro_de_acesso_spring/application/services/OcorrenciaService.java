package com.senai.registro_de_acesso_spring.application.services;

import com.senai.registro_de_acesso_spring.application.dto.OcorrenciaSaidaDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Usuario;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.registro_de_acesso_spring.domain.enuns.StatusDaOcorrencia;
import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeOcorrencia;
import com.senai.registro_de_acesso_spring.domain.repositories.OcorrenciaRepository;
import com.senai.registro_de_acesso_spring.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OcorrenciaService {

    @Autowired
    private UsuarioRepository usuarioRepository;
@Autowired
private OcorrenciaRepository ocorrenciaRepository;

    public void criarOcorrenciaDeAtraso(String idDeAcesso){
        Optional<Usuario> usuarioOpt = usuarioRepository.findByIdAcesso(idDeAcesso);
        if(usuarioOpt.isPresent()){
            if(usuarioOpt.get() instanceof Aluno aluno){
                System.out.println("é aluno");
            }
        }
    }


    public void criarOcorrenciaDeSaida(String idDeAcesso, OcorrenciaSaidaDTO ocorrenciaSaidaDTO){
     Ocorrencia ocorrencia = new Ocorrencia();



     ocorrencia.setAluno(ocorrenciaSaidaDTO.aluno());
     ocorrencia.setProfessorResponsavel(ocorrenciaSaidaDTO.professorResponsavel());
     ocorrencia.setUnidadeCurricular(ocorrenciaSaidaDTO.unidadeCurricular());
     ocorrencia.setTipo(TipoDeOcorrencia.SAIDA_ANTECIPADA);
     ocorrencia.setDescricao(ocorrenciaSaidaDTO.descricao());
     ocorrencia.setStatus(StatusDaOcorrencia.AGUARDANDO_AUTORIZACAO);
        ocorrencia.setPedidoDeSaida(LocalDateTime.now());
        ocorrencia.setDataHoraConclusao(ocorrenciaSaidaDTO.dataHoraConclusao());
     ocorrenciaRepository.save(ocorrencia);
    }

    public List<OcorrenciaSaidaDTO> listarOcorrenciasDeSaida(){
        return ocorrenciaRepository.findAll().stream().map(
                os -> new OcorrenciaSaidaDTO(
                        os.getId(),
                        os.getAluno(),
                        os.getProfessorResponsavel(),
                        os.getUnidadeCurricular(),
                        os.getTipo(),
                        os.getDescricao(),
                        os.getStatus(),
                        os.getDataHoraConclusao(),
                        os.getDataHoraCriacao(
                )
        )).toList();
    }

    public Optional<OcorrenciaSaidaDTO> buscarOcorrenciaSaidaPorId(Long id){
        return ocorrenciaRepository.findById(id).map(
                os -> new OcorrenciaSaidaDTO(
                        os.getId(),
                        os.getAluno(),
                        os.getProfessorResponsavel(),
                        os.getUnidadeCurricular(),
                        os.getTipo(),
                        os.getDescricao(),
                        os.getStatus(),
                        os.getDataHoraConclusao(),
                        os.getDataHoraCriacao()
                )
        );
    }

    public boolean atualizarOcorrenciaSaidaPorId(Long id, OcorrenciaSaidaDTO ocorrenciaSaidaDTO){
        return ocorrenciaRepository.findById(id).map(
                os -> {
                    os.setAluno(ocorrenciaSaidaDTO.aluno());
                    os.setTipo(ocorrenciaSaidaDTO.tipoDeOcorrencia());
                    os.setProfessorResponsavel(ocorrenciaSaidaDTO.professorResponsavel());
                    os.setUnidadeCurricular(ocorrenciaSaidaDTO.unidadeCurricular());
                    os.setDescricao(ocorrenciaSaidaDTO.descricao());
                    os.setStatus(ocorrenciaSaidaDTO.statusDaOcorrencia());
                    os.setDataHoraCriacao(ocorrenciaSaidaDTO.dataHoraCriacao());
                    os.setDataHoraConclusao(ocorrenciaSaidaDTO.dataHoraConclusao());
                    ocorrenciaRepository.save(os);
                    return true;
                }).orElse(false);
    }

    public boolean deletarOcorrenciaSaida(Long id){
        if (ocorrenciaRepository.existsById(id)){
            ocorrenciaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


