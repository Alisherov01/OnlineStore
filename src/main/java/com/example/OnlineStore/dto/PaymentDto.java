package com.example.OnlineStore.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PaymentDto {
    private Timestamp time;

    private String orderSum;
}
