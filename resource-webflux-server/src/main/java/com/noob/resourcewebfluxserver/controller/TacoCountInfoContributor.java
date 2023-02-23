package com.noob.resourcewebfluxserver.controller;

import com.noob.resourcewebfluxserver.dao.TacoRepository;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// /info 端点会包含这个数据
@Component
public class TacoCountInfoContributor implements InfoContributor {

    private final TacoRepository tacoRepository;

    public TacoCountInfoContributor(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @Override
    public void contribute(Info.Builder builder) {
        Long tacoCount = tacoRepository.count().block();
        Map<String, Object> tacoMap = new HashMap<>();

        tacoMap.put("count", tacoCount);
        builder.withDetail("taco-stats", tacoMap);
    }
}
