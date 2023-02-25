package com.noob.adminserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .authorizeExchange(auth -> auth
                        .pathMatchers("/login", "/logout", "/error").permitAll()
                        .pathMatchers("/variables.css", "/sba-settings.js", "/assets/**").permitAll()
                        //.pathMatchers("/actuator/**", "/applications", "/", "/instances", "/instances/**").authenticated()

                        // 很难区分哪些需要鉴权还是不用鉴权
                        .anyExchange().permitAll()
                )
                .formLogin()
                .and()
                .csrf().disable();

        return serverHttpSecurity.build();
    }

    /*@Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}53cr3t")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }*/
}
