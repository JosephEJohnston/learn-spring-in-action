package com.noob.tacocloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    /**
     * <p>当应用启动的时候，应用上下文中所有实现了 CommandLineRunner 或 ApplicationRunner 的 bean 都会执行其 run() 方法</p>
     * <p>两者的区别是参数</p>
     */
    /*@Bean
    public CommandLineRunner dataLoader(IngredientRepository repo) {
        return args -> {
            repo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
            repo.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
        };
    }*/
}
