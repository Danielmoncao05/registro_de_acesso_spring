package com.senai.registro_de_acesso_spring;

import com.senai.registro_de_acesso_spring.infrastructure.mqtt.MqttSubscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class  RegistroDeAcessoSpringApplication {

	public static void main(String[] args) {
		MqttSubscriber.iniciarMqtt();
		SpringApplication.run(RegistroDeAcessoSpringApplication.class, args);
	}

}
