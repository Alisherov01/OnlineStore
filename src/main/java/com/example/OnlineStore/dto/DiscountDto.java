package com.example.OnlineStore.dto;

import com.example.OnlineStore.enums.ProductType;
import lombok.Data;

@Data
public class DiscountDto {

    private String productName;

    private String brand;

    private ProductType productType;

    private Integer productPrice;

    private Integer sumDiscount;

    private Integer productWithDiscount;
}