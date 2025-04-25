package domain.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UnidadesCurriculares {
    private String nome;
    /*list<Professor>*/
    private LocalTime cargaHoraria;
}
