package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.ProductDto;
import com.example.OnlineStore.mappers.ProductMapper;
import com.example.OnlineStore.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {
    ProductService service;
    ProductMapper mapper;

    @GetMapping("/api/products")
    public List<ProductDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/api/products/{id}")
    public ProductDto getById(@PathVariable Long id) throws Exception {
        return service.getById(id);
    }
}
