package com.senai.registro_de_acesso_spring.domain.repositories.turmaRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.turma.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    List<Turma> findByAtivoTrue();
}