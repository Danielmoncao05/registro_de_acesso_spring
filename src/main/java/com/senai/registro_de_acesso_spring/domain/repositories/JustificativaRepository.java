package com.senai.registro_de_acesso_spring.domain.repositories;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JustificativaRepository extends JpaRepository<Justificativa, Long> {
}
