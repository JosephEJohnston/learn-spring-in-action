package com.noob.commons.model;

import com.noob.commons.model.security.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        /*return new User(
                username, passwordEncoder.encode(password),
                fullname, street, city, state, zip, phone);*/

        return new User(
                username, passwordEncoder.encode(password),
                "ROLE_ADMIN");
    }

}
