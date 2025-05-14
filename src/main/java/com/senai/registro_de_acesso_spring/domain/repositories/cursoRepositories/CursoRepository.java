package com.senai.registro_de_acesso_spring.domain.repositories.cursoRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByAtivoTrue();
}