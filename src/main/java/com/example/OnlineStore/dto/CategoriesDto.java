package com.example.OnlineStore.dto;

import com.example.OnlineStore.entity.Products;
import lombok.Data;

import java.util.List;

@Data
public class CategoriesDto {

    private String categoryName;

    private List<Products> products;

}
