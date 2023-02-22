package com.noob.emailserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "taco-cloud.api")
@Component
public class ApiProperties {
    private String url;
}
