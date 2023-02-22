package com.noob.commons.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
public class IngredientRef {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ingredient;

    protected IngredientRef() {

    }
}
