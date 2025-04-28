package domain.entities;

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
public class Curso {
    private String nome;
    private TipoCurso tipoCurso;
    private List<UnidadesCurriculares> unidadeCurricular;
    private LocalTime cargaHoraria;
    private LocalTime tolerancia;
}
