package domain.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Usuario {
    private int id_Usuario;
    private int id_Acesso;
    private int id_Matricula;
    private String nome;
    private boolean status;
    private String email;
    private String senha;
}
