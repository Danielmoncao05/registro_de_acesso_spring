package domain.entities.usuarios.Aluno;

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
public class Justificativa {
    private String tipo;
    private String descricao;
    /*anexo*/
    private LocalTime hora;
    private LocalDate data;
    private int quantidadeDias;
    private boolean status;
}
