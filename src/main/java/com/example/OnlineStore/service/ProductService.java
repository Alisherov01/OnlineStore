package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.ProductDto;
import com.example.OnlineStore.entity.Products;
import com.example.OnlineStore.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    ProductRepo productRepo;

    public List<Products> getAll(){
        return productRepo.findAll();
    }

    public ProductDto getById(Long id) throws Exception {
        Optional<Products> user = productRepo.findById(id);
        ProductDto dto = new ProductDto();
        if (user.isPresent()) {
            dto.setProductName(user.get().getProductName());
            dto.setProductColor(user.get().getProductColor());
            dto.setProductSize(user.get().getProductSize());
            dto.setBrand(user.get().getBrand());
            dto.setProductType(user.get().getProductType());
            dto.setProductPrice(user.get().getProductPrice());
            dto.setCategories(user.get().getCategories());
        } else {
            throw new Exception("Пользователя с такой " + id + " не существует");
        }
        return dto;
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
