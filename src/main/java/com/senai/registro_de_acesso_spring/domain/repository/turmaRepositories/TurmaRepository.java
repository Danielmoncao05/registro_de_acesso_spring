package com.senai.registro_de_acesso_spring.domain.repository.turmaRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.turma.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
}