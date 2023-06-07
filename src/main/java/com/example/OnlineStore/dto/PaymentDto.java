package com.example.OnlineStore.dto;

import com.example.OnlineStore.entity.Users;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentDto {

    private Users users;

    private LocalDate time = LocalDate.now();

}
