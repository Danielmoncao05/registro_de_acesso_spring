package domain.entity;

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
    /*diaSemana(enum)*/
    private List<UnidadesCurriculares>unidadeCurricular;
    /*list<Professor> e list<AM?>*/
}
