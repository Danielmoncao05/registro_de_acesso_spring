package com.senai.registro_de_acesso_spring.presentation.dtos;

public record ProfessorDTO(Long id, Integer idDeAcesso, String nome, String telefone, String email, String foto) {
}