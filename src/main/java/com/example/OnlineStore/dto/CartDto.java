package com.example.OnlineStore.dto;

import com.example.OnlineStore.entity.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long id;
    private List<Products> products;
}
