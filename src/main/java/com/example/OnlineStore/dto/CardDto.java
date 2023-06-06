package com.example.OnlineStore.dto;

import com.example.OnlineStore.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    private Users users;
    private String cardName;
    private Integer cartNumber;
    private Integer CVVCode;
    private BigDecimal cardBalance;
}