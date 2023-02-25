package com.noob.adminserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .authorizeExchange(auth -> auth
                        /*.pathMatchers("/login", "/logout", "/error", "/instances").permitAll()
                        .pathMatchers("/variables.css", "/sba-settings.js", "/assets/**").permitAll()
                        .pathMatchers("/actuator/**", "/applications", "/").authenticated()*/

                        // 很难区分哪些需要鉴权还是不用鉴权
                        .anyExchange().permitAll())
                /*.formLogin()
                .and()*/
                .csrf().disable();

        return serverHttpSecurity.build();
    }
}
