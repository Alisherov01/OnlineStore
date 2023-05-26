package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.UserDto;
import com.example.OnlineStore.entity.Users;
import com.example.OnlineStore.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsersService {
    UserRepo userRepo;

    public Long createUser(UserDto dto){
        Users users = new Users();
        users.setUserName((dto.getUserName()));
        users.setEmail((dto.getEmail()));
        users.setPassword((dto.getPassword()));
        return userRepo.save(users).getId();
    }


}
