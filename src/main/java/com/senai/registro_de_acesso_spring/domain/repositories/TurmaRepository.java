package com.senai.registro_de_acesso_spring.domain.repositories;

import com.senai.registro_de_acesso_spring.domain.entity.turma.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma,Long> {
}
