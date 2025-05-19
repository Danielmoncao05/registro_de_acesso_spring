package com.senai.registro_de_acesso_spring.domain.repositories;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Usuario;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {


}
