package com.senai.registro_de_acesso_spring.domain.entity.usuarios;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@DiscriminatorValue("AQV")
public class AQV extends Usuario {
}