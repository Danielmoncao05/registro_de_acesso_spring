package com.senai.registro_de_acesso_spring.domain.repository.turmaRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.turma.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Long> {
}