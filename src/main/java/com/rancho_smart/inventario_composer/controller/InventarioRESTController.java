package com.rancho_smart.inventario_composer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rancho_smart.AnimalDTO;
import com.rancho_smart.inventario_composer.kafka.KafkaService;
import com.rancho_smart.inventario_composer.service.AnimalService;

import reactor.core.publisher.Mono;

@RestController
public class InventarioRESTController {

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private AnimalService animalService;

    @PostMapping("/crear-animal")
    public Mono<ResponseEntity<AnimalDTO>> createAnimal(@RequestBody Object animalDTO) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(animalDTO);
        System.out.println(animalDTO.toString());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        return animalService.createAnimal(animalDTO)
            .flatMap(response -> {
                kafkaService.enviarMensajeAnimalCreado(response);
                return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(response));
            })
            .onErrorResume(error -> 
                Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null))
            );
    }
}
