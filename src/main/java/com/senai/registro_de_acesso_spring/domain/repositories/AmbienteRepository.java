package com.senai.registro_de_acesso_spring.domain.repositories;

import com.senai.registro_de_acesso_spring.domain.entity.curso.Ambiente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmbienteRepository extends JpaRepository<Ambiente,Long> {
}
