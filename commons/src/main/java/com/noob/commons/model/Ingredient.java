package com.noob.commons.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@ToString
@Entity
public class Ingredient {

    @Id
    private String id;
    private String name;
    private String code;
    private Type type;

    public Ingredient() {

    }

    public Ingredient(String id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Ingredient(String code) {
        this.code = code;
    }

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
