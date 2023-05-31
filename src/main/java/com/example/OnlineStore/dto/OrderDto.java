package com.example.OnlineStore.dto;


import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class OrderDto {

    private String fullName;

    private LocalDate orderTime = LocalDate.now();

}
