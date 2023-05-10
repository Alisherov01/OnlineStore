package com.example.OnlineStore.dto;


import lombok.Data;

import java.sql.Timestamp;
@Data
public class OrderDto {
    private Long id;

    private Timestamp orderTime;

}
