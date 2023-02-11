package com.noob.resourceserver.service;

import com.noob.commons.model.Ingredient;
import org.springframework.data.repository.CrudRepository;


public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
