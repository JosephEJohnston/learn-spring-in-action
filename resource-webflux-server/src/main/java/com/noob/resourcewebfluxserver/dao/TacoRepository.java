package com.noob.resourcewebfluxserver.dao;


import com.noob.resourcewebfluxserver.model.Taco;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TacoRepository extends ReactiveCrudRepository<Taco, Long> {

}
