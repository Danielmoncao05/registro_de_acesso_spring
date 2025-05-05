package com.senai.registro_de_acesso_spring.domain.repositories;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
}