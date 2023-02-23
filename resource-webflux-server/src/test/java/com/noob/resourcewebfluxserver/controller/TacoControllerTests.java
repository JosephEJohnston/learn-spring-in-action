package com.noob.resourcewebfluxserver.controller;

import com.noob.resourcewebfluxserver.dao.TacoRepository;
import com.noob.resourcewebfluxserver.model.Taco;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


public class TacoControllerTests {

    @Test
    public void shouldReturnRecentTacos() {
        Taco[] tacos = {
                testTaco(1L), testTaco(2L),
                testTaco(3L), testTaco(4L) };
        Flux<Taco> tacoFlux = Flux.just(tacos);

        TacoRepository tacoRepo = Mockito
                .mock(TacoRepository.class);

        Mockito.when(tacoRepo.findAll())
                .thenReturn(tacoFlux); // 模拟 tacoRepo.findAll() 的返回值

        WebTestClient testClient = WebTestClient
                .bindToController(new TacoController(tacoRepo))
                .build();

        testClient.get().uri("/api/tacos?recent")
                .exchange() // 提交请求
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$[0].id").isEqualTo(tacos[0].getId().toString())
                .jsonPath("$[0].name").isEqualTo("Taco 1")
                .jsonPath("$[1].id").isEqualTo(tacos[1].getId().toString())
                .jsonPath("$[1].name").isEqualTo("Taco 2")
                .jsonPath("$[2].id").isEqualTo(tacos[2].getId().toString())
                .jsonPath("$[2].name").isEqualTo("Taco 3")
                .jsonPath("$[18]").doesNotExist();


        /*
        // 可以用本地 json 数据做断言
        ClassPathResource recentsResource =
                new ClassPathResource("/tacos/recent-tacos.json");
        String recentsJson = StreamUtils.copyToString(
                recentsResource.getInputStream(), Charset.defaultCharset());
        testClient.get().uri("/api/tacos?recent")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json(recentsJson);
        */

        /*
        testClient.get().uri("/api/tacos?recent")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Taco.class)
                .contains(Arrays.copyOf(tacos, 12));
        */
    }

    private Taco testTaco(Long number) {
        Taco taco = new Taco();
        taco.setId(number);
        taco.setName("Taco " + number);
        /*List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(
                new Ingredient("INGA", "Ingredient A", Ingredient.Type.WRAP));
        ingredients.add(
                new Ingredient("INGB", "Ingredient B", Ingredient.Type.PROTEIN));
        taco.setIngredients(ingredients);*/
        return taco;
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldSaveATaco() {
        TacoRepository tacoRepo = Mockito
                .mock(TacoRepository.class);
        WebTestClient testClient = WebTestClient
                .bindToController(new TacoController(tacoRepo))
                .build();

        Mono<Taco> unsavedTacoMono = Mono.just(testTaco(1L));
        Taco savedTaco = testTaco(1L);
        Flux<Taco> savedTacoMono = Flux.just(testTaco(1L));

        Mockito.when(tacoRepo.saveAll(Mockito.any(Mono.class)))
                .thenReturn(savedTacoMono);

        testClient.post()
                .uri("/api/tacos")
                .contentType(MediaType.APPLICATION_JSON)
                .body(unsavedTacoMono, Taco.class)
                .exchange()
                .expectStatus()
                .isCreated()
            .expectBody(Taco.class)
                .isEqualTo(savedTaco);
    }
}
