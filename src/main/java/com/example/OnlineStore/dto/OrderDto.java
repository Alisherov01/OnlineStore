package com.example.OnlineStore.dto;


import com.example.OnlineStore.entity.Products;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class OrderDto {

    private String fullName;

    private String address;

    private LocalDate orderTime = LocalDate.now();

    private List<Products> products;
}
