package com.example.OnlineStore.dto;

import com.example.OnlineStore.entity.Products;
import lombok.Data;

import java.util.List;
@Data
public class CartDto {
    private Long id;

    private String ordersSum;

    private String discountSum;

    private String productAmount;

    private List<Products> products;
}
