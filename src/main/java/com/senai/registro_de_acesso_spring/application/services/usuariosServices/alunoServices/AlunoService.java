package com.senai.registro_de_acesso_spring.application.services.usuariosServices.alunoServices;

import org.springframework.stereotype.Service;

@Service
public class AlunoService {
    /*
    @Autowired
    private AlunoRepository alunoRepo;

    public void cadastrar(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();

        aluno.setNome(alunoDTO.nome());
        aluno.setTelefone(alunoDTO.telefone());
        aluno.setEmail(alunoDTO.email());
        /*
         nao é necessario a foto do aluno para cadastrar e as ocorerncias serao criada quando necessario apenas

        alunoRepo.save(aluno);
    }

    public List<AlunoDTO> listarAlunos() {
        return alunoRepo.findAll().stream().map(aluno -> new AlunoDTO(
                aluno.getId(),
                aluno.getIdDeAcesso(),
                aluno.getNome(),
                aluno.getTelefone(),
                aluno.getEmail(),
                aluno.getFoto(),
                aluno.getOcorrencia(),
                aluno.getJustificativa()
        )).toList(); // pegar tudo?? | como pegar separado
    }
*/
}
