package com.rancho_smart.inventario_composer.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

@Service
public class TopicCreatorService {

    private final AdminClient adminClient;

    public TopicCreatorService(AdminClient adminClient) {
        this.adminClient = adminClient;
    }

    public void crearTopicoSiNoExiste(String nombreTopico) {
        try {
            ListTopicsResult topics = adminClient.listTopics();
            boolean exists = topics.names().get().contains(nombreTopico);

            if (!exists) {
                NewTopic topic = new NewTopic(nombreTopico, 1, (short) 1);
                adminClient.createTopics(Collections.singletonList(topic));
                System.out.println("Tópico creado: " + nombreTopico);
            } else {
                System.out.println("El tópico ya existe: " + nombreTopico);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}