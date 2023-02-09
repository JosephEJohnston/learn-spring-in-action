package com.noob.tacocloud.model;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.noob.tacocloud.utils.TacoUDRUtils;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Table("tacos")
public class Taco {

    // 作为分区键
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID id = Uuids.timeBased();

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    // 作为集群键
    // 集群键用于确定一个分区内数据行的顺序
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING)
    private Date createdAt = new Date();

    @Size(min = 1, message = "You must choose at least 1 ingredient")
    @Column("ingredients") // 将列表映射到 ingredients 列
    private List<IngredientUDT> ingredients;

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(TacoUDRUtils.toIngredientUDT(ingredient));
    }
}
