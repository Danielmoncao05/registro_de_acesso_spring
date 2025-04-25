package com.senai.registro_de_acesso_spring.presentation.dtos;

public record UsuarioDTO(Long id, Integer idDeAcesso, String nome, String telefone, String email, String foto) {
}