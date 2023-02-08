package com.noob.tacocloud.dao;

import com.noob.tacocloud.model.Ingredient;
import org.springframework.data.repository.CrudRepository;


public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
