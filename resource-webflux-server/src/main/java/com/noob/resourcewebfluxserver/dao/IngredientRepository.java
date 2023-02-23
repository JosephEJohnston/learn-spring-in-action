package com.noob.resourcewebfluxserver.dao;

import com.noob.resourcewebfluxserver.model.Ingredient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IngredientRepository extends ReactiveCrudRepository<Ingredient, Long> {
    Mono<Ingredient> findBySlug(String slug);
}
