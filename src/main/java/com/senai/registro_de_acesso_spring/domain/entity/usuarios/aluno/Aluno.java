package com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno;

import com.senai.registro_de_acesso_spring.domain.entity.turma.SubTurma;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Usuario;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
@DiscriminatorValue("ALUNO") // associação da tabela tipo de usuario | determina o nome que será escrito na coluna criada no Usuario
public class Aluno extends Usuario {
    @OneToMany(mappedBy = "aluno") // determina a relacao | associaca a coluna aluno da tabela ocorrencia |
    private List<Ocorrencia> ocorrencias;

    @OneToMany(mappedBy = "aluno") // ujm para muitos
    private List<Justificativa> justificativas;

    @ManyToMany // muitos para muitos | noa precisa de associacao pois ja esta
    private List<SubTurma> subTurmas;
}