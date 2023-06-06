package com.example.OnlineStore.dto;

import com.example.OnlineStore.enums.ProductType;
import lombok.Data;

@Data
public class DiscountProductDto {
    private String productName;

    private String brand;

    ProductType productType;

    private String productPrice;

    private String productDiscount;
}
