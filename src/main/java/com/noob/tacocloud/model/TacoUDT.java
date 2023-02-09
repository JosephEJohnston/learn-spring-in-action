package com.noob.tacocloud.model;

import lombok.Data;

import java.util.List;

@Data
public class TacoUDT {
    private final String name;
    private final List<IngredientUDT> ingredients;
}
