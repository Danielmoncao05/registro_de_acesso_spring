package com.senai.registro_de_acesso_spring.application.service.usuariosServices.alunoServices;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.alunoDTOs.OcorrenciaDTO;
import com.senai.registro_de_acesso_spring.domain.entity.turma.Semestre;
import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.HorarioPadrao;
import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.HorarioSemanal;
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
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.time.temporal.ChronoUnit;
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
    private OcorrenciaServiceDomain ocorrenciaServiceDomain;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public boolean verificarAluno(String idAcesso) {
        return usuarioRepository.findByIdAcesso(idAcesso)
                .map(usuario -> {
                    if (usuario instanceof Aluno) {
                        System.out.println("É aluno");
                        return true;
                    } else {
                        System.out.println("Não é aluno");
                        return false;
                    }
                })
                .orElse(false);
    }

    @Transactional
    public void gerarOcorrenciaDeAtraso(String idAcesso){
       Optional<Aluno> alunoOpt = alunoRepository.findByIdAcesso(idAcesso);
        Aluno aluno;
       if(alunoOpt.isPresent()){
           aluno = alunoOpt.get();

           LocalTime horaEntrada = aluno.getSubTurma().getTurma().getHorarioEntrada();
           Integer tolerancia = aluno.getSubTurma().getTurma().getCurso().getToleranciaMinutos();
           System.out.println(horaEntrada+", "+tolerancia);
           LocalTime agora = LocalTime.of(10, 35); //Horario Teste

           long tempoDecorrido = Duration.between(horaEntrada.plusMinutes(tolerancia), agora).toMinutes();

           System.out.println(tempoDecorrido);

           System.out.println(tempoDecorrido / aluno.getSubTurma().getTurma().getCurso().getTipo().getMinutosPorAula() );

           long mesesEntre = ChronoUnit.MONTHS.between(aluno.getSubTurma().getTurma().getDataInicial(), LocalDate.now());
           int indiceSemestreAtual = (int) (mesesEntre / 6) + 1;

           System.out.println("Indice semestre atual: " + indiceSemestreAtual);

           //TODO teste horário semanal
           aluno.getSubTurma().getSemestres().stream().forEach(System.out::println);

           HorarioPadrao horarioPadrao = aluno.getSubTurma().getSemestres().get(indiceSemestreAtual).getHorarioPadrao();
           System.out.println("Horario padrao: " + horarioPadrao);
           System.out.println(aluno.getSubTurma().getSemestres().toString());


//           Ocorrencia ocorrencia = new Ocorrencia(
//                   TipoDeOcorrencia.ATRASO,
//                   StatusDaOcorrencia.AGUARDANDO_AUTORIZACAO,
//                   LocalDateTime.now(),
//                   null, //DataHoraConclusao
//                   aluno,
//
//           );
       }
    }

    public void notificarAQV(){
        System.out.println("Notificando AQV...");
        System.out.println("Aprovado!");
        notificarProfessor();
    }   //TODO

    public void notificarProfessor(){
        System.out.println("Notificando professor...");
        System.out.println("Aprovado!");
    }   //TODO

    public List<OcorrenciaDTO> listarOcorrencia() {
        return ocorrenciaRepository.findAll()// ocorrenciaRepository.findByStatusDaOcorrencia(StatusDaOcorrencia.APROVADO)
                .stream().map(OcorrenciaDTO::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<OcorrenciaDTO> buscarOcorrenciaPorId(Long id) {
        return ocorrenciaRepository.findById(id)
                .filter(ocorrencia -> ocorrencia.getStatus() == StatusDaOcorrencia.APROVADO) // expressao lambda | nao tenho certeza se dá certo ainda(testar)
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

    //Métodos de para geração de ocorrência de saída antecipada
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

    public void gerarOcorrenciaDeSaidaAntecipada(OcorrenciaDTO dto) {
        ocorrenciaRepository.save(dto.fromDTO());
    }
}