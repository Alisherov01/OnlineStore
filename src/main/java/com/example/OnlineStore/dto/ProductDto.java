package com.example.OnlineStore.dto;

import com.example.OnlineStore.entity.Categories;
import com.example.OnlineStore.enums.ProductType;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;

    private String productName;

    private String productColor;

    private String productSize;

    private String brand;

    ProductType productType;

    private String productPrice;

    private Categories categories;
}
