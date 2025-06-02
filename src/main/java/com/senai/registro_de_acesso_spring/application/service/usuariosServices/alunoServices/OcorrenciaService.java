package com.senai.registro_de_acesso_spring.application.service.usuariosServices.alunoServices;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.OcorrenciaDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.AQV;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Usuario;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.registro_de_acesso_spring.domain.enums.StatusDaOcorrencia;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeOcorrencia;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.AQVRepository;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.ProfessorRepository;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.UsuarioRepository;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.alunoRepositories.AlunoRepository;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.alunoRepositories.OcorrenciaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OcorrenciaService {
    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AQVRepository aqvRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // CRUD de Ocorrência
    public void registrarOcorrencia(OcorrenciaDTO dto) {
        ocorrenciaRepository.save(dto.fromDTO());
    }

    public List<OcorrenciaDTO> listarOcorrencia() {
        return ocorrenciaRepository.findAll() // ocorrenciaRepository.findByStatusDaOcorrencia(StatusDaOcorrencia.APROVADO)
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

    // Metódo importado temporariamente para verificação de Aluno
    private boolean verificarAluno(String idAcesso) {
        usuarioRepository.findByIdAcesso(idAcesso).map(usuario -> {
            if(usuario instanceof Aluno) {
                System.out.println("Aluno");
                return true;
            } else {
                System.out.println("Não é Aluno");
                return false;
            }
        });
        return false;
    }

    // TODO: Processo de Ocorrência de Atraso

    // Processo de Ocorrência de Saída Antecipada
    @Transactional
    public void solicitarSaidaAntecipada(OcorrenciaDTO dto) {
        Aluno aluno = alunoRepository.findById(dto.alunoId())
                .orElseThrow(() -> new RuntimeException("Aluno(a) não encontrado(a)"));

        AQV aqv = aqvRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("AQV não encontrado(a)"));

        Professor professor = professorRepository.findById(dto.professorResponsavelId())
                .orElseThrow(() -> new RuntimeException("Professor(a) não encontrado(a)"));

        Ocorrencia ocorrencia = dto.fromDTO();

        ocorrencia.setProfessorResponsavel(professor);
        ocorrencia.setDataHoraCriacao(LocalDateTime.now());
        ocorrencia.setAluno(aluno);
        ocorrencia.setTipo(TipoDeOcorrencia.SAIDA_ANTECIPADA);

        mudarStatusEEnviaOcorrencia(
                StatusDaOcorrencia.AGUARDANDO_AUTORIZACAO,
                aqv,
                ocorrencia
        );
    }

    public void decidirSaida(OcorrenciaDTO dto) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));

        if (dto.status() == StatusDaOcorrencia.REPROVADO) {
            mudarStatusEEnviaOcorrencia(
                    StatusDaOcorrencia.REPROVADO,
                    ocorrencia.getAluno(),
                    ocorrencia
            );
        } else {
            mudarStatusEEnviaOcorrencia(
                    StatusDaOcorrencia.AGUARDANDO_CIENCIA_PROFESSOR,
                    ocorrencia.getProfessorResponsavel(),
                    ocorrencia
            );
        }
    }

    public void confirmarCiencia(OcorrenciaDTO dto) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));

        mudarStatusEEnviaOcorrencia(
                StatusDaOcorrencia.APROVADO,
                ocorrencia.getAluno(),
                ocorrencia
        );
    }

    public void mudarStatusEEnviaOcorrencia(StatusDaOcorrencia status, Usuario usuarioDestino, Ocorrencia ocorrencia) {
        ocorrencia.setStatus(status);

        ocorrenciaRepository.save(ocorrencia);

        messagingTemplate.convertAndSendToUser(
                usuarioDestino.getUsername(),
                "/queue/ocorrencia",
                OcorrenciaDTO.toDTO(ocorrencia)
        );
    }
}