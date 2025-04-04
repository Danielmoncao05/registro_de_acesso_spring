package com.senai.registro_de_acesso_spring.repository;

import com.senai.registro_de_acesso_spring.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
}
