package com.senai.registro_de_acesso_spring.domain.repositories.usuariosRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    List<Professor> findByAtivoTrue();
}