package com.senai.registro_de_acesso_spring.user_interface.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ocorrencia")
public class OcorrenciaController {

    public void processarMqtt(String payload){
        System.out.println(payload);
    }

}
