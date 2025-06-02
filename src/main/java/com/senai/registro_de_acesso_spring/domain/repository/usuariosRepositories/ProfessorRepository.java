package com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    List<Professor> findByAtivoTrue();
    // Buscar Professor pela ordem do id ascendente
    Optional<Professor> findFirstByOrderByIdAsc();
}