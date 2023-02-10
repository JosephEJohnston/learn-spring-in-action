package com.noob.tacocloud.tmp.rest;

import com.noob.tacocloud.model.Ingredient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class RestTemplateTmp {

    @Resource
    private RestTemplate rest;

    public Ingredient getIngredientById(String ingredientId) {
        return rest.getForObject("http://localhost:8080/ingredients/{id}",
                Ingredient.class, ingredientId);
    }

    public Ingredient getIngredientByIdWithEntity(String ingredientId) {
        ResponseEntity<Ingredient> responseEntity = rest.getForEntity(
                "http://localhost:8080/ingredients/{id}", Ingredient.class, ingredientId);
        log.info("Fetched time: {}",
                responseEntity.getHeaders().getDate());
        return responseEntity.getBody();
    }

    public void updateIngredient(Ingredient ingredient) {
        rest.put("http://localhost:8080/ingredients/{id}",
                ingredient, ingredient.getId());
    }

    public void deleteIngredient(Ingredient ingredient) {
        rest.delete("http://localhost:8080/ingredients/{id}", ingredient.getId());
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return rest.postForObject("http://localhost:8080/ingredients",
                ingredient, Ingredient.class);
    }
}
