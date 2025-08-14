package com.senai.registro_de_acesso_spring.domain.entity.usuarios;

import com.senai.registro_de_acesso_spring.domain.enums.TipoDeUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity // indica que possuirá tabela
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // define a estratégia - join table(pega e junta), single table uma única tabela, possui tudo que as classes concretas possuem
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING) // nome da coluna e o que será escrito na mesma | vê qual o tipo da entidade e grava em uma coluna , type determina o valor "nome/id", utilizando string
@Data

abstract public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    //TODO fazer depois -> not null | unique | qtd carateres
    @Column(nullable = false)
    protected String nome;

    protected String cpf; // usado como login
    protected LocalDate dataNascimento;
    protected String idAcesso;
    protected String email;

    @Column(nullable = false, unique = true)
    protected String username;
    protected String senha;
    protected String telefone;
    protected String foto;
    protected boolean ativo;

    @ElementCollection(fetch = FetchType.EAGER) // faz uma coleçao dos elementos no banco de dados | tipo de busca -> ansiosa ?
    protected List<String> permissoes;

    @Enumerated(EnumType.STRING)
    private TipoDeUsuario tipoDeUsuario;
}