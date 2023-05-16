package com.example.OnlineStore.dto;

import com.example.OnlineStore.entity.Products;
import lombok.Data;


import java.util.List;
@Data
public class CategoriesDto {
    private Long id;

    private String categoryName;
    private Integer productAmount;

    private List<Products> products;
}
