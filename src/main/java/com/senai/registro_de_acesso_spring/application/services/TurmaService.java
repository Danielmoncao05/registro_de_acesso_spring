package com.senai.registro_de_acesso_spring.application.services;

import com.senai.registro_de_acesso_spring.application.dto.TurmaDTO;
import com.senai.registro_de_acesso_spring.domain.entity.curso.Curso;
import com.senai.registro_de_acesso_spring.domain.entity.turma.Turma;
import com.senai.registro_de_acesso_spring.domain.repositories.CursoRepository;
import com.senai.registro_de_acesso_spring.domain.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public void adicionarTurma (TurmaDTO turmaDTO){
        Curso curso = cursoRepository.findById(turmaDTO.cursoId()).orElseThrow();
        Turma turma = new Turma();
        turma.setNome(turmaDTO.nome());
        turma.setPeriodo(turmaDTO.periodo());
        turma.setDataInicial(turmaDTO.dataInicial());
        turma.setHorarioEntrada(turmaDTO.horarioEntrada());
        turma.setQuantidadeSemestres(turmaDTO.quantidadeSemestres());
        turma.setQuantidadeAulasPorDia(turmaDTO.quantidadeAulasPorDia());
        turma.setCurso(curso);
        turma.setSubTurma(new ArrayList<>());
        turmaRepository.save(turma);
    }

    public List<TurmaDTO> listarTurmas(){
        return turmaRepository.findAll().stream().map(t ->
                new TurmaDTO(
                        t.getId(),
                        t.getNome(),
                        t.getPeriodo(),
                        t.getDataInicial(),
                        t.getHorarioEntrada(),
                        t.getQuantidadeSemestres(),
                        t.getQuantidadeAulasPorDia(),
                        t.getCurso().getId()
                )
        ).toList();
    }

    public Optional<TurmaDTO> buscarTurmaPorId(Long id){
        return turmaRepository.findById(id).map(t ->
                new TurmaDTO(
                        t.getId(),
                        t.getNome(),
                        t.getPeriodo(),
                        t.getDataInicial(),
                        t.getHorarioEntrada(),
                        t.getQuantidadeSemestres(),
                        t.getQuantidadeAulasPorDia(),
                        t.getCurso().getId()
                )
        );
    }

    public boolean atualizarTurmaPorId(Long id, TurmaDTO turmaDTO){
        return turmaRepository.findById(id).map(t ->
        {
            Curso curso = cursoRepository.findById(turmaDTO.cursoId()).orElseThrow();
            t.setNome(turmaDTO.nome());
            t.setPeriodo(turmaDTO.periodo());
            t.setDataInicial(turmaDTO.dataInicial());
            t.setHorarioEntrada(turmaDTO.horarioEntrada());
            t.setQuantidadeSemestres(turmaDTO.quantidadeSemestres());
            t.setQuantidadeAulasPorDia(turmaDTO.quantidadeAulasPorDia());
            t.setCurso(curso);
            turmaRepository.save(t);
            return true;
        }).orElse(false);
    }

    public boolean deletarTurma(Long id){
        if (turmaRepository.existsById(id)){
            turmaRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
