package com.senai.registro_de_acesso_spring.domain.entity.usuarios;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // define a estratégia - join table(pega e junta), single table uma única tabela, possui tudo que as classes concretas possuem
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING) // nome da coluna e o que será escrito na mesma | vê qual o tipo da entidade e grava em uma coluna , type determina o valor "nome/id", utilizando string
@Data
abstract public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String idAcesso;

    @NotNull
    @Size (max = 50)
    protected String nome;

    protected String cpf;
    protected LocalDate dataNascimento;
    protected String email;
    protected String senha;
    protected String telefone;
    protected String foto;

    @ElementCollection(fetch = FetchType.EAGER) // faz uma coleçao dos elementos no banco de dados | tipo de busca -> ansiosa ?
    protected List<String> permissoes;

    @Column(name = "ativo")
    protected boolean ativo = true;
}