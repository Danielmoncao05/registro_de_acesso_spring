package com.senai.registro_de_acesso_spring.application.service.usuariosServices.alunoServices;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.OcorrenciaDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.registro_de_acesso_spring.domain.enums.StatusDaOcorrencia;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeOcorrencia;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.ProfessorRepository;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.UsuarioRepository;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.alunoRepositories.AlunoRepository;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.alunoRepositories.OcorrenciaRepository;
import com.senai.registro_de_acesso_spring.domain.service.OcorrenciaService.OcorrenciaServiceDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OcorrenciaService {
    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private OcorrenciaServiceDomain ocorrenciaServiceRN;

    public void registrarOcorrenciaSaida(OcorrenciaDTO dto) {
        ocorrenciaRepository.save(dto.fromDTO());
    }

    public boolean verificarAluno(String idAcesso) {
        usuarioRepository.findByIdAcesso(idAcesso).map(usuario -> {
            if (usuario instanceof Aluno) {
                System.out.println("É aluno");
                return true;
            } else {
                System.out.println("Não é aluno");
                return false;
            }
        });
        return false;
    }

    public void gerarOcorrenciaDeAtraso(String idAcesso){
        if (verificarAluno(idAcesso)){
            alunoRepository.findByIdAcesso(idAcesso).map(aluno -> {
                LocalTime horaEntrada = aluno.getSubTurma().getTurma().getHorarioEntrada();
                Integer tolerancia = aluno.getSubTurma().getTurma().getCurso().getToleranciaMinutos();
                if (ocorrenciaServiceRN.verificarAtraso(horaEntrada, tolerancia)){
                    Ocorrencia ocorrencia = new Ocorrencia(
                            TipoDeOcorrencia.ATRASO,
                            null,
                            StatusDaOcorrencia.AGUARDANDO_AUTORIZACAO,
                            LocalDateTime.now(),
                            null,
                            aluno.getId(),
                            //TODO: encontrar professor atual para que se possa registar a ocorrência
                            );
                }
            });
        }
    }

    public void notificarAQV(){

    }

    public void notificarProfessor(){

    }

    public List<OcorrenciaDTO> listarOcorrencia() {
        return ocorrenciaRepository.findAll()// ocorrenciaRepository.findByStatusDaOcorrencia(StatusDaOcorrencia.APROVADO)
                .stream().map(OcorrenciaDTO::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<OcorrenciaDTO> buscarOcorrenciaPorId(Long id) {
        return ocorrenciaRepository.findById(id)
                // .filter(ocorrencia -> ocorrencia.getStatus() == StatusDaOcorrencia.APROVADO) // expressao lambda | nao tenho certeza se dá certo ainda(testar)
                .map(OcorrenciaDTO::toDTO);
    }

    public boolean atualizarOcorrencia(Long id, OcorrenciaDTO dto) {
        return ocorrenciaRepository.findById(id).map(ocorrencia -> {
            Ocorrencia ocorrenciaAtualizada = dto.fromDTO();

            ocorrencia.setTipo(ocorrenciaAtualizada.getTipo());
            ocorrencia.setDescricao(ocorrenciaAtualizada.getDescricao());
            ocorrencia.setStatus(ocorrenciaAtualizada.getStatus());
            ocorrencia.setDataHoraCriacao(ocorrenciaAtualizada.getDataHoraCriacao());
            ocorrencia.setDataHoraConclusao(ocorrenciaAtualizada.getDataHoraConclusao());
            ocorrencia.setAluno(ocorrenciaAtualizada.getAluno());
            ocorrencia.setProfessorResponsavel(ocorrenciaAtualizada.getProfessorResponsavel());
            ocorrencia.setUnidadeCurricular(ocorrenciaAtualizada.getUnidadeCurricular());

            ocorrenciaRepository.save(ocorrencia);
            return true;
        }).orElse(false);
    }

    public boolean deletarOcorrencia(Long id) {
        if(ocorrenciaRepository.existsById(id)) {
            ocorrenciaRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

}