package com.noob.adminserver.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .authorizeExchange(auth -> auth
                        .pathMatchers("/login", "/logout", "/error").permitAll()
                        .pathMatchers("/actuator/info", "/actuator/health").permitAll()
                        .pathMatchers("/variables.css", "/sba-settings.js",
                                "/assets/**", "/favicon.ico").permitAll()
                        //.pathMatchers("/actuator/**", "/applications", "/", "/instances", "/instances/**").authenticated()
                        .anyExchange().hasAnyRole("USER")

                        // 很难区分哪些需要鉴权还是不用鉴权
                        //.anyExchange().permitAll()
                )
                .formLogin()
                .and()
                .httpBasic(Customizer.withDefaults())
                .csrf().disable();

        return serverHttpSecurity.build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }
}
