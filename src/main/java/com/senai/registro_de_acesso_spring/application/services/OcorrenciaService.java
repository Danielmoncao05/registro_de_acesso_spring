package com.senai.registro_de_acesso_spring.application.services;

import com.senai.registro_de_acesso_spring.application.dto.OcorrenciaSaidaDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Usuario;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.registro_de_acesso_spring.domain.repositories.OcorrenciaRepository;
import com.senai.registro_de_acesso_spring.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     ocorrencia.setTipo(ocorrenciaSaidaDTO.tipoDeOcorrencia());
     ocorrencia.setDescricao(ocorrenciaSaidaDTO.descricao());
     ocorrencia.setStatus(ocorrenciaSaidaDTO.statusDaOcorrencia());
     ocorrencia.setDataHoraConclusao(ocorrenciaSaidaDTO.horaDeSaida());
     ocorrencia.setDataHoraCriacao(ocorrenciaSaidaDTO.horaPedido());
     ocorrenciaRepository.save(ocorrencia);
    }

    public List<OcorrenciaSaidaDTO> listarOcorrenciasDeSaida(){
        return ocorrenciaRepository.findAll().stream().map(
                os -> new OcorrenciaSaidaDTO(
                        os.getId(),
                        os.getTipo(),
                        os.getDescricao(),
                        os.getStatus(),
                        os.getDataHoraConclusao(),
                        os.getDataHoraCriacao()
                )
        ).toList();
    }

    public Optional<OcorrenciaSaidaDTO> buscarOcorrenciaSaidaPorId(Long id){
        return ocorrenciaRepository.findById(id).map(
                os -> new OcorrenciaSaidaDTO(
                        os.getId(),
                        os.getTipo(),
                        os.getDescricao(),
                        os.getStatus(),
                        os.getDataHoraConclusao(),
                        os.getDataHoraCriacao()
                )
        );
    }

    public boolean atualizarOcorrenciaSaida(Long id, OcorrenciaSaidaDTO ocorrenciaSaidaDTO){
        return ocorrenciaRepository.findById(id).map(
                os ->
                os.setTipo(ocorrenciaSaidaDTO.tipoDeOcorrencia()));
    }

}
