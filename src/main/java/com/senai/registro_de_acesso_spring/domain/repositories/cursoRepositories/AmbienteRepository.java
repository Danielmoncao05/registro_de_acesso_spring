package com.senai.registro_de_acesso_spring.domain.repositories.cursoRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.curso.Ambiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {
    List<Ambiente> findByAtivoTrue();
}