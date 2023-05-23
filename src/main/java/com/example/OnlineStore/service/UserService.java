package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.UserLogin;
import com.example.OnlineStore.dto.UserRegister;
import com.example.OnlineStore.entity.Users;
import com.example.OnlineStore.enums.UserRoles;
import com.example.OnlineStore.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepo userRepo;

    private UserRegister mapToDto(Users user) {
        return new UserRegister(
                user.getUserName(),
                user.getEmail(),
                user.getPassword());
    }

    public UserRegister registerUser(String username, String email, String password) {
        Users user = new Users();
        user.setUserName(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserRoles(UserRoles.USER);

        userRepo.save(user);
        return mapToDto(user);
    }

 /*   public boolean authenticateUser(String email, String password) {
        UserLogin user = userRepo.userLogin(email,password);
        if (user == null) {
            return false;
        }
        return false;
    }*/
}

