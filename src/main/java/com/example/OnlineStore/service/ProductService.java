package com.example.OnlineStore.service;

import com.example.OnlineStore.entity.Products;
import com.example.OnlineStore.models.ProductDto;
import com.example.OnlineStore.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    ProductRepo productRepo;

    private ProductDto mapToDto(Products products){
        return new ProductDto(products.getId());
    }


    public List<Products> getAll(){
        return productRepo.findAll();
    }

    public Products getById(Long id){
        return productRepo.findById(id).get();
    }


}
