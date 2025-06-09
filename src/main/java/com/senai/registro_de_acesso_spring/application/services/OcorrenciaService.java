package com.senai.registro_de_acesso_spring.application.services;

import com.senai.registro_de_acesso_spring.application.dto.OcorrenciaDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Usuario;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.registro_de_acesso_spring.domain.enuns.StatusDaOcorrencia;
import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeOcorrencia;
import com.senai.registro_de_acesso_spring.domain.repositories.OcorrenciaRepository;
import com.senai.registro_de_acesso_spring.domain.repositories.UsuarioRepository;
import com.senai.registro_de_acesso_spring.domain.validador.ValidadorOcorrenciaSaida;
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


    public void criarOcorrenciaDeSaida(String idDeAcesso, OcorrenciaDTO dto){
     ValidadorOcorrenciaSaida.validarIdade(dto.aluno());

     Ocorrencia ocorrencia = new Ocorrencia();
     ocorrencia.setAluno(dto.aluno());
     ocorrencia.setProfessorResponsavel(dto.professorResponsavel());
     ocorrencia.setUnidadeCurricular(dto.unidadeCurricular());
     ocorrencia.setTipo(TipoDeOcorrencia.SAIDA_ANTECIPADA);
     ocorrencia.setDescricao(dto.descricao());
     ocorrencia.setStatus(StatusDaOcorrencia.AGUARDANDO_AUTORIZACAO);
        ocorrencia.setPedidoDeSaida(LocalDateTime.now());
        ocorrencia.setDataHoraConclusao(dto.dataHoraConclusao());
     ocorrenciaRepository.save(ocorrencia);
    }

    public List<OcorrenciaDTO> listarOcorrenciasDeSaidaPorIdDeAluno(String idDeAcesso){
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findByAlunoAndTipo(idDeAcesso, TipoDeOcorrencia.SAIDA_ANTECIPADA);
        return ocorrencias.stream().map(
                os -> new OcorrenciaDTO(
                        os.getId(),
                        os.getAluno(),
                        os.getProfessorResponsavel(),
                        os.getUnidadeCurricular(),
                        os.getTipo(),
                        os.getDescricao(),
                        os.getStatus(),
                        os.getDataHoraConclusao(),
                        os.getPedidoDeSaida(
                )
        )).toList();
    }

    public Optional<OcorrenciaDTO> buscarOcorrenciaSaidaPorId(Long id){
        return ocorrenciaRepository.findById(id).map(
                os -> new OcorrenciaDTO(
                        os.getId(),
                        os.getAluno(),
                        os.getProfessorResponsavel(),
                        os.getUnidadeCurricular(),
                        os.getTipo(),
                        os.getDescricao(),
                        os.getStatus(),
                        os.getDataHoraConclusao(),
                        os.getPedidoDeSaida()
                )
        );
    }

    public boolean atualizarOcorrenciaSaidaPorId(Long id, OcorrenciaDTO dto){
        return ocorrenciaRepository.findById(id).map(
                os -> {
                    os.setAluno(dto.aluno());
                    os.setTipo(dto.tipoDeOcorrencia());
                    os.setProfessorResponsavel(dto.professorResponsavel());
                    os.setUnidadeCurricular(dto.unidadeCurricular());
                    os.setDescricao(dto.descricao());
                    os.setStatus(dto.statusDaOcorrencia());
                    os.setPedidoDeSaida(dto.PedidoDeSaida());
                    os.setDataHoraConclusao(dto.dataHoraConclusao());
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


