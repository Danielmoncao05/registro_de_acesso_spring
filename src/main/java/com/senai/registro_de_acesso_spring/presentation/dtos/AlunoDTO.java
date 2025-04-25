package com.senai.registro_de_acesso_spring.presentation.dtos;

import com.senai.registro_de_acesso_spring.domain.entities_BAGUNCADO.Justificativa;
import com.senai.registro_de_acesso_spring.domain.entities_BAGUNCADO.Ocorrencia;

import java.util.List;

public record AlunoDTO(Long id, Integer idDeAcesso, String nome, String telefone, String email, String foto, List<Ocorrencia> ocorrencia, List<Justificativa> justificativa) {
}
