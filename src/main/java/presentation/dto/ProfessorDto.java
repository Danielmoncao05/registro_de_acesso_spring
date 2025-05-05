package presentation.dto;

import domain.entities.turma.horarios.Turma;
import domain.entities.curso.UnidadesCurriculares;

import java.util.List;

public record ProfessorDto(Long id, String nome, int idade, List<Turma> turmas, List<UnidadesCurriculares> unidadesCurriculares) {
}
