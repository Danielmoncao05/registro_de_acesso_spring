package com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno;

import com.senai.registro_de_acesso_spring.domain.entity.turma.SubTurma;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@DiscriminatorValue("ALUNO") // associação da tabela tipo de usuario | determina o nome que será escrito na coluna criada no Usuario
@Data
public class Aluno extends Usuario {
    @OneToMany(mappedBy = "aluno") // determina a relacao | associaca a coluna aluno da tabela ocorrencia |
    private List<Ocorrencia> ocorrencias;

    @OneToMany(mappedBy = "aluno") // ujm para muitos
    private List<Justificativa> justificativas;

    @ManyToOne // muitos para muitos | nao precisa de associacao pois ja esta
    @JoinColumn(name = "sub_turma_id")
    private SubTurma subTurma;
}