package com.senai.registro_de_acesso_spring.application.services.cursoServices;

import com.senai.registro_de_acesso_spring.application.dto.cursoDTOs.CursoDTO;
import com.senai.registro_de_acesso_spring.domain.entity.curso.Curso;
import com.senai.registro_de_acesso_spring.domain.repositories.cursoRepositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CursoService {
    @Autowired
    CursoRepository cursoRepository;

    public void cadastrarCurso(CursoDTO dto) {
        cursoRepository.save(dto.fromDTO());
    }

    public List<CursoDTO> listarCurso() {
        return cursoRepository.findByAtivoTrue()
                .stream().map(CursoDTO::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CursoDTO> buscarCursoPorId(Long id) {
        return cursoRepository.findById(id)
                .filter(Curso::isAtivo)
                .map(CursoDTO::toDTO);
    }

    public boolean atualizarCurso(Long id, CursoDTO dto) {
        return cursoRepository.findById(id).map(curso -> {
            Curso cursoAtualizado = dto.fromDTO();

            curso.setId(cursoAtualizado.getId());
            curso.setTitulo(cursoAtualizado.getTitulo());
            curso.setTipo(cursoAtualizado.getTipo());
            curso.setCarga_horaria(cursoAtualizado.getCarga_horaria());
            curso.setToleranciaMinutos(cursoAtualizado.getToleranciaMinutos());
            curso.setQuantidadeSemestres(cursoAtualizado.getQuantidadeSemestres());
            curso.setUnidades_curriculares(cursoAtualizado.getUnidades_curriculares());

            cursoRepository.save(curso);
            return true;
        }).orElse(false);
    }

    public boolean inativarCurso(Long id) {
        return cursoRepository.findById(id).map(curso -> {
            curso.setAtivo(false);

            cursoRepository.save(curso);
            return true;
        }).orElse(false);
    }
}