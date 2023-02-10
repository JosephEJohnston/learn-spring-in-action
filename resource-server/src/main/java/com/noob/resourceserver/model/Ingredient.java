package com.noob.resourceserver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
public class Ingredient {

    @Id
    private String id;
    private String name;
    private Type type;

    public Ingredient() {

    }


    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
