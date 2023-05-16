package com.example.OnlineStore.dto;

import com.example.OnlineStore.entity.Products;
import lombok.Data;

import java.util.List;
@Data
public class CartDto {
    private Long id;

    private Integer ordersSum;

    private Integer discountSum;

    private Integer productAmount;

    private List<Products> products;
}
