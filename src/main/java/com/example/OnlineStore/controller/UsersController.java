package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.UserRegister;
import com.example.OnlineStore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UsersController {
    UserService userService;
    @PostMapping("/register")
    public UserRegister userRegister(@RequestParam String username,
                                     @RequestParam String email,
                                     @RequestParam String password) {
        return userService.registerUser(username,email,password);
    }

  /*  @PostMapping("/login")
    public boolean loginUser(@RequestParam String username, @RequestParam String password) {
        return userService.authenticateUser(username, password);
    }*/
}
