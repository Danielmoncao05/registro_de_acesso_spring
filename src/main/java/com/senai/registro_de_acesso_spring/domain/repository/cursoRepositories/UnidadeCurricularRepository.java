package com.senai.registro_de_acesso_spring.domain.repository.cursoRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.curso.UnidadeCurricular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeCurricularRepository extends JpaRepository<UnidadeCurricular,Long> {
    void deleteAllByCursoId(Long cursoId);
}