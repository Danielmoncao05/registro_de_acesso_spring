package domain.entities;

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
    private diaSemana diaSemana;
    private List<UnidadesCurriculares>unidadeCurricular;
    private List<Professor> professores;
     /*list<Ambiente>*/
}
