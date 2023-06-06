package com.example.OnlineStore.dto;

import com.example.OnlineStore.entity.Products;
import com.example.OnlineStore.entity.Users;
import lombok.Data;

import java.util.List;
@Data
public class CartDto {
    private Long id;
    private List<Products> products;
}
