package com.noob.resourceserver.config;

import com.noob.commons.dao.UserRepository;
import com.noob.commons.model.security.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                return user;
            }

            throw new UsernameNotFoundException("User '" + username + "' not found.");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/design", "/orders").hasRole("USER")
                        //.requestMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/ingredients")
                            .hasAuthority("SCOPE_writeIngredients")
                        .requestMatchers(HttpMethod.DELETE, "/api/ingredients")
                            .hasAuthority("SCOPE_deleteIngredients")
                        .requestMatchers("/", "/api/orders", "/error").permitAll()
                        .requestMatchers("/images/**", "/favicon.ico").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin()
                //.loginPage("http://localhost:8081/login")
                //.defaultSuccessUrl("/design", true)
                .and()
                //.oauth2Login()
                // 可以在 login 页面中提供 Facebook 登录的链接
                //.loginPage("/login")
                //.and()
                .logout()
                .and()
                .csrf().disable()
                .oauth2ResourceServer(server -> server.jwt(
                        jwt -> jwt.decoder(
                                NimbusJwtDecoder
                                        .withJwkSetUri("http://localhost:8081/oauth2/jwks")
                                        .build())))
                .build();
    }
}
