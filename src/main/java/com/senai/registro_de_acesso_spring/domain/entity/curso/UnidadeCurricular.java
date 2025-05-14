package com.senai.registro_de_acesso_spring.domain.entity.curso;

import com.senai.registro_de_acesso_spring.domain.entity.usuarios.Professor;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Entity
@Data
public class UnidadeCurricular { // obs: Strings temporários, execeto nome
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String nome;
    private Integer cargaHorariaTotal;

    @ElementCollection
    private Map<Integer, Integer> cargaHorariaPorSemestre; // semestre -> horas | Map -> coleção | associacao entre grandezas | pega uma grandeza e por referência combina com outra | associacao de dois ou mais valores em um conjunto só | primeiro Integer referencia ao primerio semesyte , o segundo Integer referencia a quantidadde de horas

    @ManyToOne
    private Curso curso;

    @ManyToMany
    private List<Professor> professor;
}