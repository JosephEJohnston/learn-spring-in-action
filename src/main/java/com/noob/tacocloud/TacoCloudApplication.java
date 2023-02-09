package com.noob.tacocloud;

import com.noob.tacocloud.dao.IngredientRepository;
import com.noob.tacocloud.dao.UserRepository;
import com.noob.tacocloud.model.Ingredient;
import com.noob.tacocloud.model.security.User;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@SpringBootApplication
public class TacoCloudApplication {

    @Resource
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    /**
     * <p>当应用启动的时候，应用上下文中所有实现了 CommandLineRunner 或 ApplicationRunner 的 bean 都会执行其 run() 方法</p>
     * <p>两者的区别是参数</p>
     */
    @Bean
    public CommandLineRunner dataLoader(IngredientRepository ingredientRepository, UserRepository userRepository) {
        return args -> {
            ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
            ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
            ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
            ingredientRepository.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
            ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
            ingredientRepository.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
            ingredientRepository.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
            ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
            ingredientRepository.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
            ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));

            userRepository.save(new User("user", passwordEncoder.encode("123456"),
                    "test_fullname", "test_street", "test_city",
                    "test_state", "test_zip", "test_phone_number"));
        };
    }

    /*
    // 可由自动配置实现
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("taco_schema.sql")
                .addScripts("user_data.sql", "ingredient_data.sql")
                .build();
    }*/
}
