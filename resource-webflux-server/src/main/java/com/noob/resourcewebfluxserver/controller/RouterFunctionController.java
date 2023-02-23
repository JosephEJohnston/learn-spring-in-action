package com.noob.resourcewebfluxserver.controller;

import com.noob.commons.model.Taco;
import com.noob.resourcewebfluxserver.dao.TacoRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Objects;


@Controller
public class RouterFunctionController {

    @Resource
    private TacoRepository tacoRepo;

    @Bean
    public RouterFunction<?> helloRouterFunction() {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello"),
                        request -> ServerResponse
                                .ok()
                                .body(Mono.just("Hello World!"), String.class))
                .andRoute(RequestPredicates.GET("/bye"),
                        request -> ServerResponse
                                .ok()
                                .body(Mono.just("See ya!"), String.class));
    }

    @Bean
    public RouterFunction<?> routerFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/api/tacos").
                and(RequestPredicates.queryParam("recent", Objects::nonNull)), this::recent)
                .andRoute(RequestPredicates.POST("/api/tacos"), this::postTaco);
    }

    private Mono<ServerResponse> recent(ServerRequest request) {
        return ServerResponse.ok()
                .body(tacoRepo.findAll().take(12), Taco.class);
    }

    private Mono<ServerResponse> postTaco(ServerRequest request) {
        return request.bodyToMono(Taco.class)
                .flatMap(taco -> tacoRepo.save(taco))
                .flatMap(savedTaco -> ServerResponse
                        .created(URI.create(
                                "http://localhost:8080/api/tacos/" +
                                        savedTaco.getId()))
                        .body(savedTaco, Taco.class));
    }
}
