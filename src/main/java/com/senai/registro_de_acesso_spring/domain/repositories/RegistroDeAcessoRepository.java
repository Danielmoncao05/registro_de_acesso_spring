package com.senai.registro_de_acesso_spring.domain.repositories;

import com.senai.registro_de_acesso_spring.domain.entities_BAGUNCADO.RegistroDeAcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroDeAcessoRepository extends JpaRepository<RegistroDeAcesso, Long> {
}