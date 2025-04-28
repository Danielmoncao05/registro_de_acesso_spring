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
public class Professor extends Usuario {
    private List<Turma> turmas;
    private  List<UnidadesCurriculares> unidadesCurriculares;
}
