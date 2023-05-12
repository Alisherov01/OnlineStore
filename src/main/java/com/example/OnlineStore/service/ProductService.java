package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.ProductDto;
import com.example.OnlineStore.entity.Products;
import com.example.OnlineStore.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    ProductRepo productRepo;

    public List<ProductDto> getAll() {
        List<ProductDto> dtos = new ArrayList<>();
        List<Products> products = productRepo.findAll();
        for (Products p : products) {
            ProductDto dto = new ProductDto();
            dto.setProductName(p.getProductName());
            dto.setProductColor(p.getProductColor());
            dto.setProductSize(p.getProductSize());
            dto.setBrand(p.getBrand());
            dto.setProductType(p.getProductType());
            dto.setProductPrice(p.getProductPrice());
            dtos.add(dto);
        }
        return dtos;
    }

    public ProductDto getById(Long id) throws Exception {
        Optional<Products> products = productRepo.findById(id);
        ProductDto dto = new ProductDto();
        if (products.isPresent()) {
            dto.setProductName(products.get().getProductName());
            dto.setProductColor(products.get().getProductColor());
            dto.setProductSize(products.get().getProductSize());
            dto.setBrand(products.get().getBrand());
            dto.setProductType(products.get().getProductType());
            dto.setProductPrice(products.get().getProductPrice());
            dto.setCategories(products.get().getCategories());
        } else {
            throw new Exception("Товара с такими данными не существует");
        }
        return dto;
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
