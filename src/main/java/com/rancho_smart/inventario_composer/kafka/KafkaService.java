package com.rancho_smart.inventario_composer.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.rancho_smart.AnimalDTO;

@Service
public class KafkaService {
    
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private TopicCreatorService topicCreatorService;

    private static final String TOPIC = "animal_creado";

    public void enviarMensajeAnimalCreado(AnimalDTO animal) {
        topicCreatorService.crearTopicoSiNoExiste(TOPIC);
        kafkaTemplate.send(TOPIC, animal);
    }
}
