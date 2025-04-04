package com.senai.registro_de_acesso_spring.domain.entities;

import jakarta.persistence.Entity;

import java.sql.Time;
import java.util.Date;

@Entity
public class AcessRegistry {
    private User user;
    private Date date;
    private Time time;
    //https://www.devmedia.com.br/trabalhando-com-as-classes-date-calendar-e-simpledateformat-em-java/27401

}