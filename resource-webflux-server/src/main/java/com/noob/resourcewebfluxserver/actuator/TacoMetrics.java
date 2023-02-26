package com.noob.resourcewebfluxserver.actuator;

import com.noob.resourcewebfluxserver.model.Ingredient;
import com.noob.resourcewebfluxserver.model.Taco;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

import java.util.List;

// 自定义 metrics
@Component
public class TacoMetrics extends AbstractRepositoryEventListener<Taco> {

    private final MeterRegistry meterRegistry;

    public TacoMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;

        // 添加默认数据，否则报 404
        meterRegistry.counter("tacocloud", "default", "0");
    }

    // 每当保存新的 Taco 对象，它都会得到通知
    @Override
    protected void onAfterCreate(Taco taco) {
        /*List<Ingredient> ingredients = taco.getIngredients();
        for (Ingredient ingredient : ingredients) {
            meterRegistry.counter("tacocloud", "ingredient",
                    ingredient.getId()).increment();
        }*/
    }
}
