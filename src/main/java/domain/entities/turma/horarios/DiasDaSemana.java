package domain.entities.turma.horarios;

import domain.entities.curso.UnidadesCurriculares;
import domain.entities.usuarios.Professor;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class DiasDaSemana {
    private domain.entities.enuns.diaSemana diaSemana;
    private List<UnidadesCurriculares>unidadeCurricular;
    private List<Professor> professores;
     /*list<Ambiente>*/
}
