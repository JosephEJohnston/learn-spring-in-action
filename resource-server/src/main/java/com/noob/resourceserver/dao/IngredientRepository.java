package com.noob.resourceserver.dao;

import com.noob.resourceserver.model.Ingredient;
import org.springframework.data.repository.CrudRepository;


public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
