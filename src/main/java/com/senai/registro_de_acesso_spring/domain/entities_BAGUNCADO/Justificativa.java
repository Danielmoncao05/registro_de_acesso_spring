package com.senai.registro_de_acesso_spring.domain.entities_BAGUNCADO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Justificativa {
    private String tipo;
    private String descricao;
    private String anexo;
    private LocalDateTime data_hora;
    private int quantidade_dias;
    private String status;
}