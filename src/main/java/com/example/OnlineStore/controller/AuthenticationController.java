package com.example.OnlineStore.controller;

import com.example.OnlineStore.JWTToken.JsonWebTokenUtil;
import com.example.OnlineStore.dto.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody UserRequest userRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUserName(),
                userRequest.getPassword()));
        return ResponseEntity.ok(
                JsonWebTokenUtil.generateToken(userRequest.getUserName())
        );
    }
}