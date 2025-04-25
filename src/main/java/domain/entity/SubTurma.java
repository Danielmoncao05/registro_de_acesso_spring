package domain.entity;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SubTurma extends Turma {
private String nome;
/*list<aluno>*/
    private LocalTime horario;
}
