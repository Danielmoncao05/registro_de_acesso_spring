package com.senai.registro_de_acesso_spring.domain.repositories.usuariosRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.AQV;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AQVRepository extends JpaRepository<AQV, Long> {
    List<AQV> findByAtivoTrue();
}