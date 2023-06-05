package com.example.OnlineStore.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDto {

    private String fullName;

    private LocalDate orderTime = LocalDate.now();

}
