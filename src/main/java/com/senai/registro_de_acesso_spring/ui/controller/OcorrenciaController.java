package com.senai.registro_de_acesso_spring.ui.controller;

import com.senai.registro_de_acesso_spring.application.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ocorrencia")
public class OcorrenciaController {

    @Autowired
    public OcorrenciaService ocorrenciaService;

    public void processarMqtt(String payload){
        System.out.println("Payload RFID: '" + payload + "'");
    }

    //TEST
    public void buscarUsuario(String idAcesso){
        ocorrenciaService.identificarUsuario(idAcesso);
    }

}
