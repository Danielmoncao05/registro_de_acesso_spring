package domain.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Curso {
    private String nome;
    private String tipo;
    private List<UnidadesCurriculares> unidadeCurricular;
    private LocalTime cargaHoraria;
    private LocalTime tolerancia;
}
