package com.senai.registro_de_acesso_spring.domain.entities_BAGUNCADO;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ocorrencia { // obs: Strings temporários
    private String tipo;
    private String descricao;
    private String status; // utilizar ENUM ? | somente três tipos de status: APROVADO, PENDENTE, REPROVADO
    private LocalDateTime data_hora;
}