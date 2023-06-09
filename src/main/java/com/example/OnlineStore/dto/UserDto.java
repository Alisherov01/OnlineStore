package com.example.OnlineStore.dto;

import com.example.OnlineStore.enums.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userName;

    private String email;

    private String password;

    UserRoles userRoles;

}
