package com.senai.registro_de_acesso_spring.domain.service.OcorrenciaService;

import com.senai.registro_de_acesso_spring.domain.entity.curso.UnidadeCurricular;
import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.Aula;
import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.AulasDoDia;
import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.HorarioSemanal;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.enums.DiasDaSemana;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
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
        System.out.println("identificando professor");
        return aulaAtual(a).getProfessor();     //TEST
    }

    public UnidadeCurricular identificarUC(Aluno a){
        System.out.println("identificando UC");
       return aulaAtual(a).getUnidadeCurricular();  //TEST
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
        int quantidadeAulas = aulasDeHoje.getAulas().size();
        LocalTime horarioEntrada = aluno.getSubTurma().getTurma().getHorarioEntrada();

        Duration duracaoAula = Duration.ofMinutes(45); // Assume curso técnico
        LocalTime agora = LocalTime.of(10, 12); //Horario Teste

        // Se ainda não chegou o horário de início das aulas
        if (agora.isBefore(horarioEntrada)) {
            System.out.println("Aulas ainda não começaram.");
            return null;
        }

        Duration tempoDecorrido = Duration.between(horarioEntrada, agora);
        int index = (int) (tempoDecorrido.toMinutes() / duracaoAula.toMinutes());

        // Se o índice é maior do que a quantidade de aulas do dia
        if (index >= quantidadeAulas) {
            System.out.println("Aulas do dia já terminaram.");
            return null;
        }

        System.out.println("Indice da aula atual: " + index);
        System.out.println("ID Professor: " + aulasDeHoje.getAulas().get(index).getProfessor().getId() + "\nID UC: " + aulasDeHoje.getAulas().get(index).getProfessor().getId());
        System.out.println("ID Professor: " + aulasDeHoje.getAulas().get(0).getProfessor().getId() + "\nID UC: " + aulasDeHoje.getAulas().get(0).getProfessor().getId());

        return aulasDeHoje.getAulas().get(index);
    }


    public AulasDoDia aulasDeHoje(Aluno aluno){

        Enum<DiasDaSemana> hoje = diaDaSemana(LocalDate.now().getDayOfWeek());

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
            if (aulasDoDia.getDiaDaSemana().equals(hoje)){
                System.out.println("Aulas do dia encontradas!");
                return aulasDoDia;
            }else System.out.println("Aulas do dia nao encontradas");
        }
        return null;
    }   //TODO testar metodo

    public Enum<DiasDaSemana> diaDaSemana(DayOfWeek diaDaSemana){
        switch (diaDaSemana){
            case MONDAY -> {
                return DiasDaSemana.SEGUNDA;
            }
            case TUESDAY -> {
                return DiasDaSemana.TERCA;
            }
            case WEDNESDAY -> {
                return DiasDaSemana.QUARTA;
            }
            case THURSDAY -> {
                return DiasDaSemana.QUINTA;
            }
            case FRIDAY -> {
                return DiasDaSemana.SEXTA;
            }
            default -> {
                return null;
            }
        }
    }
}