package com.example.OnlineStore.dto;

import lombok.Data;

@Data
public class CardDto {
    private String fullName;
    private String cartNumber;
    private String CVVCode;
    private Integer cardBalance;
}