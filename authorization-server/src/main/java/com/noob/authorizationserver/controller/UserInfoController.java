package com.noob.authorizationserver.controller;

import com.noob.commons.model.security.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    @GetMapping
    public Map<String, Object> user(
            @AuthenticationPrincipal User user
    ) {
        if (user != null) {
            return Map.of("username", user.getUsername(),
                    "authorities", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        }
        return null;
    }
}
