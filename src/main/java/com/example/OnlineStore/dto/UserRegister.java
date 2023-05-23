package com.example.OnlineStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegister {

    private String userName;

    private String email;

    private String password;

}
