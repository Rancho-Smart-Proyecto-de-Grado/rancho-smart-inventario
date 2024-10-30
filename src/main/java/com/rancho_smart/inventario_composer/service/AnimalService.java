package com.rancho_smart.inventario_composer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.rancho_smart.AnimalDTO;

import reactor.core.publisher.Mono;

@Service
public class AnimalService {
    
    private final WebClient webClient; 

    @Value("${ANIMALES_URL}")
    private String animalesUrl;

    public AnimalService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(animalesUrl).build();
    }

    public Mono<Object> getAnimalById(Long animalId) {
        return webClient.get()
            .uri("/animales/{id}", animalId)
            .retrieve()
            .bodyToMono(Object.class);
    }

    public Mono<AnimalDTO> createAnimal(Object animalDTO) {
        return webClient.post()
            .uri(animalesUrl + "/animales")
            .bodyValue(animalDTO)
            .retrieve()
            .bodyToMono(AnimalDTO.class);
    }
}
