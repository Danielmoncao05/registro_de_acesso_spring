package domain.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Turma {
    private String nome;
    private List<SubTurma> turmas;
    private LocalTime periodo;
    private String curso;
    /*list<aluno>*/
    private LocalTime horarioEntrada;
    private LocalDate dataInicio;
    private int qtdSemestre;

}
