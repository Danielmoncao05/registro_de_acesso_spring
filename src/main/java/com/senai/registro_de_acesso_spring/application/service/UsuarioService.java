package com.senai.registro_de_acesso_spring.application.service;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    /*
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void cadastrarUsuario(UsuarioDTO dto) {
        usuarioRepository.save(dto.fromDTO());
    }

    public List<UsuarioDTO> listarAtivos() {
        return usuarioRepository.findByStatusTrue()
                .stream().map(UsuarioDTO::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .filter(Usuario::isStatus)
                .map(UsuarioDTO::toDTO);
    }

    public boolean atualizar(Long id, UsuarioDTO dto) {
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

    public boolean desativar(Long id) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setStatus(false);
            usuarioRepository.save(usuario);
            return true;
        }).orElse(false);
    }
     */
}