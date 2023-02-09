package com.noob.tacocloud.model;

import lombok.*;

@Getter
@Setter
@ToString
public class IngredientRef {
    private Long id;

    private String ingredient;

    public IngredientRef(String ingredient) {
        this.ingredient = ingredient;
    }

    protected IngredientRef() {

    }
}
