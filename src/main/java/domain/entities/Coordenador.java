package domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Coordenador extends Usuario{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "coordenador_equipe",
            joinColumns = @JoinColumn(name = "coordenador_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> equipeDeProfessores;
}
