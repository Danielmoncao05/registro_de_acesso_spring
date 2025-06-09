package com.senai.registro_de_acesso_spring.infrastructure.mqtt;

import com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers.alunoControllers.JustificativaController;
import com.senai.registro_de_acesso_spring.interface_ui.controller.usuariosControllers.alunoControllers.OcorrenciaController;
import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttSubscriber {
    private static final String BROKER = "tcp://localhost:1883";
    private static final String CLIENT_ID = "ServidorJava";
    private static final String TOPICO = "catraca/rfid";

    @Autowired
    private OcorrenciaController ocorrenciaController;

    @Autowired
    private JustificativaController justificativaController;

    @PostConstruct
    public void iniciarMqtt() {
        try {
            MqttClient client = new MqttClient(BROKER, CLIENT_ID);
            client.connect();
            client.subscribe(TOPICO, (topic, msg) -> {
                String idAcesso = new String(msg.getPayload());
                // Roda somente com a verificação se é aluno ou não
                ocorrenciaController.criarOcorrenciaAtraso(idAcesso);
                // Conexão com justificativa controller - TESTE - Roda independente de existir um usuário ou não | realizar verificação

                justificativaController.testeJustificativaFaltaIdAcesso(idAcesso);
            });
            System.out.println("Inscrito no tópico MQTT: " + TOPICO);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}

