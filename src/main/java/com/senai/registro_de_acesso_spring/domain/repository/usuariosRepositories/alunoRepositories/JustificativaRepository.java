package com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.alunoRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.registro_de_acesso_spring.domain.enums.StatusDaJustificativa;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeJustifcativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JustificativaRepository extends JpaRepository<Justificativa, Long> {
    //List<Justificativa> findByStatusDaJustificativa(StatusDaJustificativa status);

    // se funcionar eu sou incrivel
    Optional<Justificativa> findByIdAndTipo(Long idJustificativa, TipoDeJustifcativa tipo);
    // possivelmente vai dar merda
    Optional<Justificativa> findByIdAndTipoAndStatus(Long idJustificativa, TipoDeJustifcativa tipo, StatusDaJustificativa status);
}