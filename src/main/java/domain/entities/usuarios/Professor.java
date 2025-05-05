package domain.entities.usuarios;

import domain.entities.turma.horarios.Turma;
import domain.entities.curso.UnidadesCurriculares;
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
