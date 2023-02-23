package com.noob.resourcewebfluxserver.other;

import com.noob.resourcewebfluxserver.model.Ingredient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@SpringBootTest
public class WebClientTests {

    @Bean
    public WebClient webClient() {
        return WebClient.create("http://localhost:8080");
    }

    @Test
    public void getTest() {
        Long ingredientId = 1L;

        Mono<Ingredient> ingredient = WebClient.create()
                .get()
                .uri("/ingredients/{id}", ingredientId)
                .retrieve()
                .bodyToMono(Ingredient.class);

        ingredient.subscribe(i -> {

        });

        Flux<Ingredient> ingredients = WebClient.create()
                .get()
                .uri("/ingredients")
                .retrieve()
                .bodyToFlux(Ingredient.class);

        ingredients
                .timeout(Duration.ofSeconds(1))
                .subscribe(i -> {

                });

    }

    @Test
    public void otherTest(WebClient webClient) {
        Mono<Ingredient> ingredientMono = Mono.just(
                new Ingredient("INGC", "Ingredient C", Ingredient.Type.VEGGIES));

        Mono<Ingredient> result = webClient
                .post()
                .uri("/ingredients")
                .body(ingredientMono, Ingredient.class)
                .retrieve()
                .bodyToMono(Ingredient.class);

        result.subscribe(i -> {

        });


        /*
        Mono<Void> result = webClient
                .put()
                .uri("/ingredients/{id}", ingredient.getId())
                .bodyValue(ingredient)
                .retrieve()
                .bodyToMono(Void.class);
        result.subscribe();
        */

        /*
        Mono<Void> result = webClient
                .delete()
                .uri("/ingredients/{id}", ingredientId)
                .retrieve()
                .bodyToMono(Void.class);
        result.subscribe();
        */
    }

    @Test
    public void errorHandle(WebClient webClient) {
        Long ingredientId = 1L;

        Mono<Ingredient> ingredientMono = webClient
                .get()
                .uri("http://localhost:8080/ingredients/{id}", ingredientId)     .retrieve()
                .bodyToMono(Ingredient.class);

        ingredientMono.subscribe(
                ingredient -> {
                    // handle the ingredient data
                },
                error-> {
                    // deal with the error
                });


        /*
        // 自定义错误处理
        Mono<Ingredient> ingredientMono = webClient
                .get()
                .uri("http://localhost:8080/ingredients/{id}", ingredientId)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        response -> Mono.just(new UnknownIngredientException()))
                .bodyToMono(Ingredient.class);

        Mono<Ingredient> ingredientMono = webClient
                .get()
                .uri("http://localhost:8080/ingredients/{id}", ingredientId)
                .retrieve()
                .onStatus(status -> status == HttpStatus.NOT_FOUND,
                        response -> Mono.just(new UnknownIngredientException()))
                .bodyToMono(Ingredient.class);
        */



    }


    @Test
    public void exchangeRequest(WebClient webClient) {
        Long ingredientId = 1L;

        Mono<Ingredient> ingredientMono = webClient
                .get()
                .uri("http://localhost:8080/ingredients/{id}", ingredientId)

                // ClientResponse 对象可以处理完整响应
                .exchangeToMono(cr -> {
                    if (cr.headers().header("X_UNAVAILABLE").contains("true")) {
                        return Mono.empty();
                    }
                    return Mono.just(cr);
                })
                .flatMap(cr -> cr.bodyToMono(Ingredient.class));
    }
}
