package presentation.dto;

import domain.entities.Turma;
import domain.entities.UnidadesCurriculares;

import java.util.List;

public record ProfessorDto(Long id, String nome, int idade, List<Turma> turmas, List<UnidadesCurriculares> unidadesCurriculares) {
}
