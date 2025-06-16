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
        return aulaAtual(a).getProfessor();
    }

    public UnidadeCurricular identificarUC(Aluno a){
       return aulaAtual(a).getUnidadeCurricular();
    }

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

    public Aula aulaAtual(Aluno aluno) {
        AulasDoDia aulasDeHoje = aulasDeHoje(aluno);
        LocalTime now = LocalTime.now();
        int quantidadeAulas = aluno.getSubTurma().getTurma().getQuantidadeAulasPorDia();
        LocalTime horarioEntrada = aluno.getSubTurma().getTurma().getHorarioEntrada();
        //TODO achar aula dentro da lista
        return null;
    }

    public AulasDoDia aulasDeHoje(Aluno aluno){
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

        for (AulasDoDia aulasDoDia : listaDeAulas){
            if (DayOfWeek.valueOf(aulasDoDia.getDiaDaSemana().toUpperCase()).equals(hoje)){
                System.out.println("Aulas do dia encontradas!");
                return aulasDoDia;
            }else System.out.println("Aulas do dia nao encontradas");
        }

        return null;
    }   //TODO arrumar metodo
}