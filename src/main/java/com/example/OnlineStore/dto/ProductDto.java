package com.example.OnlineStore.dto;

import com.example.OnlineStore.entity.Cart;
import com.example.OnlineStore.entity.Categories;
import com.example.OnlineStore.enums.ProductType;
import lombok.Data;

import javax.persistence.*;

@Data
public class ProductDto {

    private String productName;

    private String productColor;

    private String productSize;

    private String brand;

    private ProductType productType;

    private String productPrice;

    private Categories categories;

}