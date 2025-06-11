package com.senai.registro_de_acesso_spring.domain.service.justificativaService;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Aluno;
import com.senai.registro_de_acesso_spring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.UsuarioRepository;
import com.senai.registro_de_acesso_spring.domain.repository.usuariosRepositories.alunoRepositories.OcorrenciaRepository;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class JustificativaServiceDomain {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    public boolean criarJustificativa(String idAcesso) {
        usuarioRepository.findByIdAcesso(idAcesso).map(usuario -> {
            if (usuario instanceof Aluno) {
                System.out.println("È Aluno");
                return true;
            } else {
                System.out.println("Não é Aluno");
                return false;
            }
        });
        return false;
    }

    // Validar Existência de Ocorrência para justificá-la - Dani
    public Ocorrencia validarOcorrencia(Long idOcorrencia) {
        return ocorrenciaRepository.findById(idOcorrencia)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Ocorrência com ID: " + idOcorrencia + " não encontrada")
                );
    }

}