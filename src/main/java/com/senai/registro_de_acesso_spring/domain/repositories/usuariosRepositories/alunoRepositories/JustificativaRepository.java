package com.senai.registro_de_acesso_spring.domain.repositories.usuariosRepositories.alunoRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JustificativaRepository extends JpaRepository<Justificativa, Long> {
    //List<Justificativa> findByStatusDaJustificativa(StatusDaJustificativa status);
    List<Justificativa> findByAtivoTrue();
}