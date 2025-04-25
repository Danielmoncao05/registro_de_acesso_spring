package com.senai.registro_de_acesso_spring.domain.entities_BAGUNCADO;

import jakarta.persistence.Entity;

import java.sql.Time;
import java.util.Date;

@Entity
public class RegistroDeAcesso {
    private Usuario user;
    private Date date;
    private Time time;
    //https://www.devmedia.com.br/trabalhando-com-as-classes-date-calendar-e-simpledateformat-em-java/27401

}