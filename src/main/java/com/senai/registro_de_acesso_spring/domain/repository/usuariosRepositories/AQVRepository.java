package com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.AQV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AQVRepository extends JpaRepository<AQV, Long> {
    List<AQV> findByAtivoTrue();
    // Buscar AQV pela ordem do id ascendente
    Optional<AQV> findFirstByOrderByIdAsc();
}