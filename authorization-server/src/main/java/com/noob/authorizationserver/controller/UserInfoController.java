package com.noob.authorizationserver.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    @GetMapping
    public Map<String, Object> user(
            @AuthenticationPrincipal Jwt principal
    ) {
        return principal.getClaims();
    }
}
