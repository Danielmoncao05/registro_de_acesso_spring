package com.senai.registro_de_acesso_spring.application.services.usuariosServices;

import com.senai.registro_de_acesso_spring.application.dto.usuariosDTOs.UsuarioDTO;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Usuario;
import com.senai.registro_de_acesso_spring.domain.repositories.usuariosRepositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void cadastrarUsuario(UsuarioDTO dto) {
        usuarioRepository.save(dto.fromDTO());
    }

    public List<UsuarioDTO> listarUsuariosAtivos() {
        return usuarioRepository.findByAtivoTrue()
                .stream().map(UsuarioDTO::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .filter(Usuario::isAtivo)
                .map(UsuarioDTO::toDTO);
    }

    public boolean atualizarUsuario(Long id, UsuarioDTO dto) {
        return usuarioRepository.findById(id).map(usuario -> {
            Usuario usuarioAtualizado = dto.fromDTO();

            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setDataNascimento(usuarioAtualizado.getDataNascimento());
            usuario.setCpf(usuarioAtualizado.getCpf());

            usuarioRepository.save(usuario);
            return true;
        }).orElse(false);
    }

    public boolean inativarUsuario(Long id) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setAtivo(false);

            usuarioRepository.save(usuario);
            return true;
        }).orElse(false);
    }

}