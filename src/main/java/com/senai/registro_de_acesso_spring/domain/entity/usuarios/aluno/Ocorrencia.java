package com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno;

import com.senai.registro_de_acesso_spring.domain.entity.curso.UnidadeCurricular;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import com.senai.registro_de_acesso_spring.domain.enums.StatusDaOcorrencia;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeOcorrencia;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ocorrencia {

    public Ocorrencia(TipoDeOcorrencia tipo,                // Criei este construtor para permitir a instanciação de Ocorrencias no app service.
                      String descricao,                     // O @NoArgsConstructor e o @Data aparentemente não estão sendo suficiente, porque a aplicação tem exigido o ID (que é auto-gerado) no momento da criação da entidade.
                      StatusDaOcorrencia status,            // Enquanto não seja resolvido, utilizarei este construtor como solução temporária para manter a aplicação funcionando.
                      LocalDateTime dataHoraCriacao,
                      LocalDateTime dataHoraConclusao,
                      Aluno aluno,
                      Professor professorResponsavel,
                      UnidadeCurricular unidadeCurricular) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.status = status;
        this.dataHoraCriacao = dataHoraCriacao;
        this.dataHoraConclusao = dataHoraConclusao;
        this.aluno = aluno;
        this.professorResponsavel = professorResponsavel;
        this.unidadeCurricular = unidadeCurricular;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(EnumType.STRING)
    private TipoDeOcorrencia tipo;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusDaOcorrencia status;

    private LocalDateTime dataHoraCriacao;
    private LocalDateTime dataHoraConclusao;

    @ManyToOne
    private Aluno aluno;

  @ManyToOne
    private Professor professorResponsavel;

    @ManyToOne
    private UnidadeCurricular unidadeCurricular;
}