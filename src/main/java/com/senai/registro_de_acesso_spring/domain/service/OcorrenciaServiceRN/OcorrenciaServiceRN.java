package com.senai.registro_de_acesso_spring.domain.service.OcorrenciaServiceRN;

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
import com.senai.registro_de_acesso_spring.domain.validador.ValidadorOcorrenciaSaida;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OcorrenciaServiceRN {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar Ocorrencia de Atraso com MQTT
    public void criarOcorrenicaDeAtraso(String idAcesso) {
        usuarioRepository.findByIdAcesso(idAcesso).map(usuario -> {
            if (usuario instanceof Aluno) {
                System.out.println("È Aluno");
            } else {
                System.out.println("Não é Aluno");
            }
            return null;
        });
    }

    @Transactional
    public void solicitarSaidaAntecipada(OcorrenciaDTO dto) {
        Aluno aluno = alunoRepository.findById(dto.alunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Ocorrencia ocorrencia = dto.fromDTO();

        ocorrencia.setStatus(StatusDaOcorrencia.AGUARDANDO_AUTORIZACAO);
        ocorrencia.setDataHoraCriacao(LocalDateTime.now());
        ocorrencia.setAluno(aluno);
        ocorrencia.setTipo(TipoDeOcorrencia.SAIDA_ANTECIPADA);

        Ocorrencia saved = ocorrenciaRepository.save(ocorrencia);

        messagingTemplate.convertAndSend(
                "/topic/aqv",
                OcorrenciaDTO.toDTO(saved)
        );
    }

    public void decidirSaida(OcorrenciaDTO dto) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));

        if (dto.status() == StatusDaOcorrencia.REPROVADO) {
            ocorrencia.setStatus(StatusDaOcorrencia.REPROVADO);
            ocorrenciaRepository.save(ocorrencia);
            messagingTemplate.convertAndSend(
                    "/topic/aluno/" +
                            ocorrencia.getAluno().getId(),
                    OcorrenciaDTO.toDTO(ocorrencia)
            );
            return;
        }

        ocorrencia.setStatus(StatusDaOcorrencia.AGUARDANDO_CIENCIA_PROFESSOR);

        Professor professor = professorRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        ocorrencia.setProfessorResponsavel(professor);
        Ocorrencia saved = ocorrenciaRepository.save(ocorrencia);

        messagingTemplate.convertAndSend(
                "/topic/professor/" +
                        professor.getId(),
                OcorrenciaDTO.toDTO(saved)
        );
    }

    public void confirmarCiencia(OcorrenciaDTO dto) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));

        ocorrencia.setStatus(StatusDaOcorrencia.APROVADO);
        Ocorrencia saved = ocorrenciaRepository.save(ocorrencia);

        messagingTemplate.convertAndSend(
                "/topic/aluno/" +
                        ocorrencia.getAluno().getId(),
                OcorrenciaDTO.toDTO(saved)
        );
    }

    public void criarOcorrenciaDeSaida(Long idAluno, OcorrenciaDTO dto, Aluno aluno){

         aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        ValidadorOcorrenciaSaida.validarIdade(aluno);

        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setAluno(aluno);
        ocorrencia.setProfessorResponsavel(dto.fromDTO().getProfessorResponsavel());
        ocorrencia.setUnidadeCurricular(dto.fromDTO().getUnidadeCurricular());
        ocorrencia.setTipo(TipoDeOcorrencia.SAIDA_ANTECIPADA);
        ocorrencia.setDescricao(dto.descricao());
        ocorrencia.setStatus(StatusDaOcorrencia.AGUARDANDO_AUTORIZACAO);
        ocorrencia.setDataHoraCriacao(dto.dataHoraCriacao());
        ocorrencia.setDataHoraConclusao(dto.dataHoraConclusao());
        ocorrenciaRepository.save(ocorrencia);
    }

    public List<OcorrenciaDTO> listarOcorrenciasDeSaidaPorIdDeAluno(Aluno aluno){
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findByAlunoAndTipo(aluno, TipoDeOcorrencia.SAIDA_ANTECIPADA);
        return ocorrencias.stream().map(
                os -> new OcorrenciaDTO(
                        os.getId(),
                        os.getAluno(),
                        os.getProfessorResponsavel(),
                        os.getUnidadeCurricular(),
                        os.getTipo(),
                        os.getDescricao(),
                        os.getStatus(),
                        os.getDataHoraCriacao(),
                        os.getDataHoraConclusao()
                )).toList();
    }
    //erro na sequencia?

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
                        os.getDataHoraCriacao()
                )
        );
    }

    public boolean atualizarOcorrenciaSaidaPorId(Long id, OcorrenciaDTO dto){
        return ocorrenciaRepository.findById(id).map(
                os -> {
                    os.setAluno(dto.fromDTO().getAluno());
                    os.setTipo(dto.tipo());
                    os.setProfessorResponsavel(dto.fromDTO().getProfessorResponsavel());
                    os.setUnidadeCurricular(dto.fromDTO().getUnidadeCurricular());
                    os.setDescricao(dto.descricao());
                    os.setStatus(dto.status());
                    os.setDataHoraCriacao(dto.dataHoraCriacao());
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










}