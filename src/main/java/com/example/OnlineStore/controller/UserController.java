package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.UserDto;
import com.example.OnlineStore.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private UsersService usersService;
    @PostMapping("/registration")
    public Long userRegistration(@RequestBody UserDto users){
        return usersService.createUser(users);
    }

}

