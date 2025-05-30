package com.senai.registro_de_acesso_spring.domain.repository.turmaRepositories.horariosRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.turma.horarios.HorarioPadrao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioPadraoRepository extends JpaRepository<HorarioPadrao, Long> {
}