package com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {
    List<Coordenador> findByAtivoTrue();
}