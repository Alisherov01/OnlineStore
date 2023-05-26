package com.example.OnlineStore.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class PaymentDto {

    private LocalDate time;

    private String orderSum;

}
