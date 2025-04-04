package com.senai.registro_de_acesso_spring.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer idAcess;
    private String name;
    private String phonenumber;
    private String email;
    private String photo;

    public User() {
    }

    public User(Long id, String name, String phonenumber, String email, String photo){
        this.id = id;
        this.name = name;
        this.phonenumber = phonenumber;
        this.email = email;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdAcess() {
        return idAcess;
    }

    public void setIdAcess(Integer idAcess) {
        this.idAcess = idAcess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}