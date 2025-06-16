package com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.alunoRepositories;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.registro_de_acesso_spring.domain.enums.TipoDeOcorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
    //List<Ocorrencia> findByStatusDaOcorrencia(StatusDaOcorrencia status);
    List<Ocorrencia> findByAlunoAndTipo(Aluno aluno, TipoDeOcorrencia tipo);
}