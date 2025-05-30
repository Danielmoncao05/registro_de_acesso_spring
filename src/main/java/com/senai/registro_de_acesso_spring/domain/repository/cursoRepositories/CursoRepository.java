package com.senai.registro_de_acesso_spring.domain.repository.cursoRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}