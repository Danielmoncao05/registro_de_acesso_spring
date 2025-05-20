package com.senai.registro_de_acesso_spring.domain.entity.usuarios;

import com.senai.registro_de_acesso_spring.domain.enuns.TipoDeUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity // indica que possuirá tabela
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // define a estratégia - join table(pega e junta), single table uma única tabela, possui tudo que as classes concretas possuem
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING) // nome da coluna e o que será escrito na mesma | vê qual o tipo da entidade e grava em uma coluna , type determina o valor "nome/id", utilizando string
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
abstract public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    /*
    fazer depois -> not null | unique | qtd carateres */

    @Enumerated(EnumType.STRING)
    private TipoDeUsuario tipoDeUsuario;
    protected String nome;
    protected String cpf; // usado como login
    protected LocalDate dataNascimento;
    protected String idAcesso;
    protected String email;
    protected String senha;
    protected String telefone;
    protected String foto;

    @ElementCollection(fetch = FetchType.EAGER) // faz uma coleçao dos elementos no banco de dados | tipo de busca -> ansiosa ?
    protected List<String> permissoes;
}