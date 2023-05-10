package com.example.OnlineStore.mappers;

import com.example.OnlineStore.dto.ProductDto;
import com.example.OnlineStore.entity.Products;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final ModelMapper modelMapper;

    public ProductMapper() {
        this.modelMapper = new ModelMapper();
    }

    public ProductDto mapToDto(Products product) {
        return modelMapper.map(product, ProductDto.class);
    }

    public Products mapToModel(ProductDto dto) {
        return modelMapper.map(dto, Products.class);
    }
}