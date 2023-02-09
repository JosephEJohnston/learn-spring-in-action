package com.noob.tacocloud.utils;

import com.noob.tacocloud.model.Ingredient;
import com.noob.tacocloud.model.IngredientUDT;

public class TacoUDRUtils {
    public static IngredientUDT toIngredientUDT(Ingredient ingredient) {
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }
}
