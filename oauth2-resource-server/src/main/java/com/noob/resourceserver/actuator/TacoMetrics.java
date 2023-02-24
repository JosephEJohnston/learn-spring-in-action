package com.noob.resourceserver.actuator;

import com.noob.commons.model.Ingredient;
import com.noob.commons.model.Taco;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

import java.util.List;

// AbstractRepositoryEventListener 这个类在 r2dbc 没有
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
        List<Ingredient> ingredients = taco.getIngredients();
        for (Ingredient ingredient : ingredients) {
            meterRegistry.counter("tacocloud", "ingredient",
                    ingredient.getId()).increment();
        }
    }
}
