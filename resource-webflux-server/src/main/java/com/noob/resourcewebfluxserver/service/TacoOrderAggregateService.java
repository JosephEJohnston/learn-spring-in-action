package com.noob.resourcewebfluxserver.service;

import com.noob.resourcewebfluxserver.dao.OrderRepository;
import com.noob.resourcewebfluxserver.dao.TacoRepository;
import com.noob.resourcewebfluxserver.model.Taco;
import com.noob.resourcewebfluxserver.model.TacoOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TacoOrderAggregateService {

    private final TacoRepository tacoRepo;
    private final OrderRepository orderRepo;

    public Mono<TacoOrder> save(TacoOrder tacoOrder) {
        return Mono.just(tacoOrder)
                .flatMap(order -> {
                    List<Taco> tacos = order.getTacos();
                    order.setTacos(new ArrayList<>());

                    return tacoRepo.saveAll(tacos)
                            .map(taco -> {
                                order.addTaco(taco);
                                return order;
                            })
                            .last();
                })
                .flatMap(orderRepo::save);
    }

    public Mono<TacoOrder> findById(Long id) {
        return orderRepo
                .findById(id)
                .flatMap(order -> tacoRepo
                        .findAllById(order.getTacoIds())
                        .map(taco -> {
                            order.addTaco(taco);
                            return order;
                        })
                        .last());
    }

}
