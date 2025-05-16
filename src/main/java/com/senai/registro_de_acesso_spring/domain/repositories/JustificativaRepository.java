package com.senai.registro_de_acesso_spring.domain.repositories;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JustificativaRepository extends JpaRepository<Justificativa, Long> {

}
