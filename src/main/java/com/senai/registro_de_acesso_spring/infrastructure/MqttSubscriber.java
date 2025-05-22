package com.senai.registro_de_acesso_spring.infrastructure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttSubscriber {

    private static final String BROKER = "tcp://localhost:1883";
    private static final String CLIENTE_ID = "ServidorJava";
    private static final String TOPICO = "catraca/rfid";




}
