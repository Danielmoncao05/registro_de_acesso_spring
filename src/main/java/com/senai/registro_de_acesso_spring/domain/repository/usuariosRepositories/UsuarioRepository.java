package com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    List<Usuario> findByAtivoTrue();
    // Buscar todos os usuarios por id de acesso
    Optional<Usuario> findByIdAcesso(String idAcesso);
    // Buscar pelo username
    Optional<Usuario> findByUsername(String username);
}