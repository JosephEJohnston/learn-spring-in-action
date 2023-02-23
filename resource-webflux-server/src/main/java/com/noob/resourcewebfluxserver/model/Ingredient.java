package com.noob.resourcewebfluxserver.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter // r2dbc 要求属性有 setter 方法
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Ingredient {

    // r2dbc 保存对象时，若 id 不为空，则视为更新操作
    @Id
    private Long id;

    private @NonNull String slug;

    private @NonNull String name;

    private @NonNull Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
