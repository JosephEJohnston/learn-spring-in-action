package com.noob.rsocketserver.controller;

import com.noob.rsocketcommon.model.GratuityIn;
import com.noob.rsocketcommon.model.GratuityOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@Controller
@Slf4j
public class GratuityController {
    // 双向发送模型
    @MessageMapping("gratuity")
    public Flux<GratuityOut> calculate(Flux<GratuityIn> gratuityInFlux) {
        return gratuityInFlux
                .doOnNext(in -> log.info("Calculating gratuity: {}", in))
                .map(in -> {
                    double percentAsDecimal = in.getPercent() / 100.0;
                    BigDecimal gratuity = in.getBillTotal()
                            .multiply(BigDecimal.valueOf(percentAsDecimal));

                    return new GratuityOut(
                            in.getBillTotal(), in.getPercent(), gratuity);
                });
    }
}
