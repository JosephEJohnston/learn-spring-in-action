package com.noob.resourceserver;

import com.noob.commons.dao.UserRepository;
import com.noob.commons.model.constants.PackageConstants;
import com.noob.commons.model.security.User;
import com.noob.commons.dao.IngredientRepository;
import com.noob.commons.dao.TacoRepository;
import com.noob.commons.model.Ingredient;
import com.noob.commons.model.Taco;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
@EntityScan(basePackages = {
        PackageConstants.RESOURCE_PACKAGE,
        PackageConstants.COMMONS_PACKAGE,
})
@EnableJpaRepositories(basePackages = {
        PackageConstants.RESOURCE_PACKAGE,
        PackageConstants.COMMONS_PACKAGE,
})
public class ResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }

    /**
     * <p>当应用启动的时候，应用上下文中所有实现了 CommandLineRunner 或 ApplicationRunner 的 bean 都会执行其 run() 方法</p>
     * <p>两者的区别是参数</p>
     */
    /*@Bean
    // @Profile("dev") // 指定 dev 配置才运行
    // @Profile("!prod") // 非 prod 配置都运行
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
    }*/

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner dataLoader(
            IngredientRepository repo,
            TacoRepository tacoRepo,
            UserRepository userRepository,
            PasswordEncoder encoder
    ) {
        return args -> {
            Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
            Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP);
            Ingredient groundBeef = new Ingredient(
                    "GRBF", "Ground Beef", Ingredient.Type.PROTEIN);
            Ingredient carnitas = new Ingredient(
                    "CARN", "Carnitas", Ingredient.Type.PROTEIN);
            Ingredient tomatoes = new Ingredient(
                    "TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES);
            Ingredient lettuce = new Ingredient(
                    "LETC", "Lettuce", Ingredient.Type.VEGGIES);
            Ingredient cheddar = new Ingredient(
                    "CHED", "Cheddar", Ingredient.Type.CHEESE);
            Ingredient jack = new Ingredient(
                    "JACK", "Monterrey Jack", Ingredient.Type.CHEESE);
            Ingredient salsa = new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE);
            Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE);
            repo.save(flourTortilla);
            repo.save(cornTortilla);
            repo.save(groundBeef);
            repo.save(carnitas);
            repo.save(tomatoes);
            repo.save(lettuce);
            repo.save(cheddar);
            repo.save(jack);
            repo.save(salsa);
            repo.save(sourCream);

            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(
                    flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
            tacoRepo.save(taco1);

            Taco taco2 = new Taco();
            taco2.setName("Bovine Bounty");
            taco2.setIngredients(Arrays.asList(
                    cornTortilla, groundBeef, cheddar, jack, sourCream));
            tacoRepo.save(taco2);

            Taco taco3 = new Taco();
            taco3.setName("Veg-Out");
            taco3.setIngredients(Arrays.asList(
                    flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
            tacoRepo.save(taco3);

            /*userRepository.save(new User("user", encoder.encode("123456"), "ROLE_ADMIN",
                    "test_fullname", "test_street", "test_city",
                    "test_state", "test_zip", "test_phone_number"));
*/
            userRepository.save(new User("user",
                    encoder.encode("123456"), "ROLE_ADMIN"));
            userRepository.save(new User("tacochef", encoder.encode("password"), "ROLE_ADMIN"));
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
