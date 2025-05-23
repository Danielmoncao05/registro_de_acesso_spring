package com.senai.registro_de_acesso_spring.domain.repositories.turmaRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.turma.SubTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubTurmaRepository extends JpaRepository<SubTurma, Long> {
}