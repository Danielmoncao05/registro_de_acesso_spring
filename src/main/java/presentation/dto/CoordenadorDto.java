package presentation.dto;

import domain.entities.Professor;

import java.time.LocalDate;
import java.util.List;

public record CoordenadorDto(Long id, String nome, int idade, List<ProfessorDto> equipe) {
}
