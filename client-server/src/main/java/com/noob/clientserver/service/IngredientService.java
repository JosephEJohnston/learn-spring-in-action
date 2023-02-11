package com.noob.clientserver.service;

import com.noob.commons.model.Ingredient;

public interface IngredientService {
    Iterable<Ingredient> findAll();

    Ingredient addIngredient(Ingredient ingredient);
}
