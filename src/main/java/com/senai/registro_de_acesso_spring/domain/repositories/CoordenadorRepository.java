package com.senai.registro_de_acesso_spring.domain.repositories;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoordenadorRepository extends JpaRepository<Coordenador, Long>{
    List<Coordenador> findByAtivoTrue();
}
