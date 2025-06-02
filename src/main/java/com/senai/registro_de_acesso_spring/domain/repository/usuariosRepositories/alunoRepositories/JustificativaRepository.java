package com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.alunoRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeJustificativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JustificativaRepository extends JpaRepository<Justificativa, Long> {
    //List<Justificativa> findByStatusDaJustificativa(StatusDaJustificativa status);
    // Listar Justificativas de Falta ou Atraso
    //List<JustificativaDTO> findByTipoDeJustificativa(TipoDeJustifcativa tipo);
    Optional<Justificativa> findByTipoDeJustificativa(TipoDeJustificativa tipo);
}