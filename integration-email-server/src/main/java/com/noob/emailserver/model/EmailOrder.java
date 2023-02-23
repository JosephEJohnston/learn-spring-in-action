package com.noob.emailserver.model;

import com.noob.commons.model.Taco;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class EmailOrder {
    private final String email;
    private List<Taco> tacos = new ArrayList<>();

    public EmailOrder(String email) {
        this.email = email;
    }

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }
}
