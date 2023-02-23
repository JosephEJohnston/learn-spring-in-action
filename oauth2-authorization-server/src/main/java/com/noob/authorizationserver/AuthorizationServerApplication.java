package com.noob.authorizationserver;

import com.noob.commons.dao.UserRepository;
import com.noob.commons.model.constants.PackageConstants;
import com.noob.commons.model.security.User;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages = {
        PackageConstants.AUTHORIZATION_PACKAGE,
        PackageConstants.COMMONS_PACKAGE,
})
@EntityScan(basePackages = {
        PackageConstants.AUTHORIZATION_PACKAGE,
        PackageConstants.COMMONS_PACKAGE,
})
@EnableJpaRepositories(basePackages = {
        PackageConstants.AUTHORIZATION_PACKAGE,
        PackageConstants.COMMONS_PACKAGE,
})
public class AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

    @Bean
    public ApplicationRunner dataLoader(
            UserRepository repo,
            PasswordEncoder encoder
    ) {
        return args -> {
            repo.save(new User("habuma", encoder.encode("password"), "ROLE_ADMIN"));
            repo.save(new User("tacochef", encoder.encode("password"), "ROLE_ADMIN"));
        };
    }
}
