package domain.entities.usuarios.Aluno;

import domain.entities.enuns.Status;
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
public class Ocorrencia {
    private String tipo;
    private String descricao;
    private Status status;
    private LocalDate data;
    private LocalTime hora;
}
