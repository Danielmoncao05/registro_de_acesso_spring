package com.senai.registro_de_acesso_spring.application.services;

import com.senai.registro_de_acesso_spring.application.dto.TurmaDTO;
import com.senai.registro_de_acesso_spring.domain.entity.curso.Curso;
import com.senai.registro_de_acesso_spring.domain.entity.turma.Turma;
import com.senai.registro_de_acesso_spring.domain.repositories.CursoRepository;
import com.senai.registro_de_acesso_spring.domain.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public void criarTurma (TurmaDTO turmaDTO){
        Curso curso = cursoRepository.findById(turmaDTO.cursoId()).orElseThrow();
        Turma turma = new Turma();
        turma.setNome(turmaDTO.nome());
        turma.setPeriodo(turmaDTO.periodo());
        turma.setDataInicial(turmaDTO.dataInicial());
    }
}
