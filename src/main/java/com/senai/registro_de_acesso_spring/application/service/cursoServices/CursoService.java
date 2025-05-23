package com.senai.registro_de_acesso_spring.application.service.cursoServices;

import com.senai.registro_de_acesso_spring.application.dto.cursoDTOs.CursoDTO;
import com.senai.registro_de_acesso_spring.domain.entity.curso.Curso;
import com.senai.registro_de_acesso_spring.domain.entity.curso.UnidadeCurricular;
import com.senai.registro_de_acesso_spring.domain.repository.cursoRepositories.CursoRepository;
import com.senai.registro_de_acesso_spring.domain.repository.cursoRepositories.UnidadeCurricularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    private UnidadeCurricularRepository unidadeCurricularRepository;

    public CursoDTO cadastrarCurso(CursoDTO dto) {
        final Curso curso = cursoRepository.save(
                new Curso(
                        dto.titulo(),
                        dto.tipo(),
                        dto.carga_horaria(),
                        dto.toleranciaMinutos()
                )
        );

        List<UnidadeCurricular> unidades = dto.unidadesCurricularesDTO()
                .stream()
                .map(
                        ucDto -> ucDto.fromDTO(curso)
                )
                .toList()
        ;

        unidadeCurricularRepository.saveAll(unidades);

        curso.setUnidadesCurriculares(unidades);

        return CursoDTO.toDTO(curso);

    }

    public List<CursoDTO> listarCursos() {
        return cursoRepository.findAll().stream().map(CursoDTO::toDTO).toList();
    }

    public Optional<CursoDTO> buscarCursoPorId(Long id) {
        return cursoRepository.findById(id).map(CursoDTO::toDTO);
    }

    public CursoDTO atualizarCurso(Long id, CursoDTO dto) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        curso.setTitulo(dto.titulo());
        curso.setTipo(dto.tipo());
        curso.setCargaHoraria(dto.carga_horaria());
        curso.setToleranciaMinutos(dto.toleranciaMinutos());

        final Curso cursoAtualizado = cursoRepository.save(curso);

        unidadeCurricularRepository.deleteAllByCursoId(curso.getId());

        List<UnidadeCurricular> novasUCs = dto.unidadesCurricularesDTO()
                .stream()
                .map(ucDTO -> ucDTO.fromDTO(cursoAtualizado))
                .toList()
        ;

        unidadeCurricularRepository.saveAll(novasUCs);

        cursoAtualizado.setUnidadesCurriculares(novasUCs);

        return CursoDTO.toDTO(cursoAtualizado);
    }

    public void deletarCurso(Long id) {
        if(cursoRepository.existsById(id)) {
            unidadeCurricularRepository.deleteAllByCursoId(id);

            cursoRepository.deleteById(id);
        }
    }
}