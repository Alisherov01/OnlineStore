package com.example.OnlineStore.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentDto {

    private LocalDate time = LocalDate.now();

    private Integer orderSum;

}
