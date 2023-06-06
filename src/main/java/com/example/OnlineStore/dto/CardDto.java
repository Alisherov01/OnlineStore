package com.example.OnlineStore.dto;

import com.example.OnlineStore.entity.Users;
import lombok.Data;

@Data
public class CardDto {
    private Users users;
    private String cardName;
    private Integer cartNumber;
    private Integer CVVCode;
    private Integer cardBalance;
}