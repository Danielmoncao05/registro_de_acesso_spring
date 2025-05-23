package com.senai.registro_de_acesso_spring.interface_ui.controller;

import com.senai.registro_de_acesso_spring.application.services.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    public void processarMqtt(String mT){
        ocorrenciaService.criarOcorrenciaDeAtraso(mT);

    }
    @PostMapping("/saida/{idAluno}")
    public void criarOcorrenciaDeSaida(String idDeAcesso, String tipoDeOcorrencia, String descricao, String statusDaOcorrencia, String horaDeSaida, String horaPedido){
        ocorrenciaService.criarOcorrenciaDeSaida(idDeAcesso, tipoDeOcorrencia, descricao, statusDaOcorrencia, horaDeSaida, horaPedido);
    }


}
