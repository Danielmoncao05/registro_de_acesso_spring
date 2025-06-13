package com.senai.registro_de_acesso_spring.domain.service.OcorrenciaService;

import com.senai.registro_de_acesso_spring.domain.entity.curso.UnidadeCurricular;
import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.Aula;
import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.AulasDoDia;
import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.HorarioSemanal;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class OcorrenciaServiceDomain {

    public boolean verificarAtraso(LocalTime horaEntrada, Integer tolerancia){
        LocalTime horarioLimite = horaEntrada.plusMinutes(tolerancia);
        LocalTime agora = LocalTime.of(9, 10);  //retorno de teste para simular atrasos
        //return LocalTime.now().isAfter(horarioLimite);      retorno correto
        return agora.isAfter(horarioLimite);
    }

    public boolean verificarHorario(LocalTime horaEntrada){
        //verificar se o horario de entrada consta antes do inicio da aula ou depois do termino da aula - para evitar erros;
        return true;
    }     //TODO

    public Professor identificarProfessor(Aluno a){
        if (existeHorarioSemanal(a)){
            return a.getSubTurma().
                    getSemestres().
                    get(semestreAtual(a)).
                    getHorariosSemanais().
                    get(horarioSemanalAtual(a)).
                    getListaDeAulasDoDia().
                    get(aulasDeHoje(a)).
                    getAulas().get(aulaAtual(a)).
                    getProfessor();
        }else {
            return a.getSubTurma().
                    getSemestres().
                    get(semestreAtual(a)).
                    getHorarioPadrao().
                    getListaDeAulasDoDia().get(aulasDeHoje(a)).
                    getAulas().get(aulaAtual(a)).
                    getProfessor();
        }
    }   //TODO fragmentar função

    public UnidadeCurricular identificarUC(Aluno a){
        if (existeHorarioSemanal(a)){
            return a.getSubTurma().
                    getSemestres().
                    get(semestreAtual(a)).
                    getHorariosSemanais().
                    get(horarioSemanalAtual(a)).
                    getListaDeAulasDoDia().
                    get(aulasDeHoje(a)).
                    getAulas().get(aulaAtual(a)).
                    getUnidadeCurricular();
        }else {
            return a.getSubTurma().
                    getSemestres().
                    get(semestreAtual(a)).
                    getHorarioPadrao().
                    getListaDeAulasDoDia().get(aulasDeHoje(a)).
                    getAulas().get(aulaAtual(a)).
                    getUnidadeCurricular();
        }
    }       //TODO fragmentar função

    public boolean existeHorarioSemanal(Aluno aluno){
        LocalDate hoje = LocalDate.now();
        LocalDate semanaAtual = hoje.with(DayOfWeek.MONDAY);
        List<HorarioSemanal> horariosSemanais = aluno.getSubTurma().getSemestres().get(semestreAtual(aluno)).getHorariosSemanais();

        if (horariosSemanais.isEmpty()){
            return false;
        }else {
            for (int i = 0; i < horariosSemanais.toArray().length; i++) {
                if (horariosSemanais.get(i).getSemanaReferencia().isEqual(semanaAtual)){
                    return true;
                }
            }
        }
        return false;
    }

    public int semestreAtual(Aluno aluno){
        LocalDate hoje = LocalDate.now();
        LocalDate dataInicial = aluno.getSubTurma().getTurma().getDataInicial();

        long mesesEntre = ChronoUnit.MONTHS.between(dataInicial, hoje);
        int semestre = (int) (mesesEntre/6) + 1;
        return semestre;
    }

    public int horarioSemanalAtual(Aluno aluno){
        LocalDate hoje = LocalDate.now();
        LocalDate semanaAtual = hoje.with(DayOfWeek.MONDAY);

        List<HorarioSemanal> horarioSemanais = aluno.getSubTurma().getSemestres().get(semestreAtual(aluno)).getHorariosSemanais();
        for (int i = 0; i < horarioSemanais.toArray().length; i++) {
            if (horarioSemanais.get(i).getSemanaReferencia().isEqual(semanaAtual)){
                return i;
            }
        }
        return 0;
    }

    public int aulaAtual(Aluno aluno) {
        DayOfWeek hoje = LocalDate.now().getDayOfWeek();
        LocalTime now = LocalTime.now();

        List<AulasDoDia> listaDeAulas;
        if (existeHorarioSemanal(aluno)) {
            listaDeAulas = aluno.getSubTurma()
                    .getSemestres()
                    .get(semestreAtual(aluno))
                    .getHorariosSemanais()
                    .get(horarioSemanalAtual(aluno))
                    .getListaDeAulasDoDia();
        } else {
            listaDeAulas = aluno.getSubTurma()
                    .getSemestres()
                    .get(semestreAtual(aluno))
                    .getHorarioPadrao()
                    .getListaDeAulasDoDia();
        }

        int quantidadeAulas = aluno.getSubTurma().getTurma().getQuantidadeAulasPorDia();
        LocalTime horarioEntrada = aluno.getSubTurma().getTurma().getHorarioEntrada();

        for (AulasDoDia aulaDoDia : listaDeAulas) {
            if (aulaDoDia.getDiaDaSemana().equals(hoje)) {
                List<Aula> aulas = aulaDoDia.getAulas();
                for (Aula aula : aulas) {

                    int ordem = aula.getOrdem();
                    LocalTime horaInicio = horarioEntrada.plusMinutes(ordem * 50);

                    if (now.isAfter(horaInicio) && now.isBefore(horaInicio.plusMinutes(50))) {
                        return aula.getOrdem();
                    }
                }
            }
        }

        return -1;
    }  //TODO Revisar Logica

    public int aulasDeHoje(Aluno aluno){
        DayOfWeek hoje = LocalDate.now().getDayOfWeek();

        List<AulasDoDia> listaDeAulas;

        if (existeHorarioSemanal(aluno)) {
            listaDeAulas = aluno.getSubTurma()
                    .getSemestres()
                    .get(semestreAtual(aluno))
                    .getHorariosSemanais()
                    .get(horarioSemanalAtual(aluno))
                    .getListaDeAulasDoDia();
        } else {
            listaDeAulas = aluno.getSubTurma()
                    .getSemestres()
                    .get(semestreAtual(aluno))
                    .getHorarioPadrao()
                    .getListaDeAulasDoDia();
        }

        for (int i = 0; i < listaDeAulas.size(); i++) {
            if (listaDeAulas.get(i).getDiaDaSemana().equals(hoje)) {
                return i;
            }
        }

        return -1;
    }
}