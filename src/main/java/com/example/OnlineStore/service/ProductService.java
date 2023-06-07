package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.ProductDto;
import com.example.OnlineStore.entity.Categories;
import com.example.OnlineStore.entity.Products;
import com.example.OnlineStore.mappers.ProductMapper;
import com.example.OnlineStore.repository.CategoriesRepo;
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
    ProductMapper productMapper;
    CategoriesRepo categoriesRepo;

    public List<ProductDto> getAll() {
        List<ProductDto> dtos = new ArrayList<>();
        List<Products> products = productRepo.findAll();
        return getProductDtos(products, dtos);
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
        } else {
            throw new Exception("Товара с такими данными не существует");
        }
        return dto;
    }

    public String deleteProduct(Long id) {
        productRepo.deleteById(id);
        return "Продукт усрешно удалён из корзины. ";
    }

    public List<ProductDto> getProductsByCategoryId(Long categoriesId) {
        List<Products> products = productRepo.getProductsByCategories(categoriesId);
        List<ProductDto> dtos = new ArrayList<>();
        return getProductDtos(products, dtos);
    }

    private List<ProductDto> getProductDtos(List<Products> products, List<ProductDto> dtos) {
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

    public Long createProduct(ProductDto dto, Long id) throws Exception {
        Products products = new Products();
        products.setProductName(dto.getProductName());
        products.setProductColor(dto.getProductColor());
        products.setProductSize(dto.getProductSize());
        products.setBrand(dto.getBrand());
        products.setProductType(dto.getProductType());
        products.setProductPrice(dto.getProductPrice());

        Categories categories = categoriesRepo.findById(id).orElseThrow(() -> new Exception("wqqqwq"));
        products.setCategories(categories);
        return productRepo.save(products).getId();
    }

    public ProductDto update(Long id, ProductDto dto) throws Exception{
        Products products = productRepo.findById(id).orElseThrow(() ->
                new Exception("Продукта с такими не существует. "));
        products.setProductName(dto.getProductName());
        products.setProductColor(dto.getProductColor());
        products.setProductSize(dto.getProductSize());
        products.setBrand(dto.getBrand());
        products.setProductType(dto.getProductType());
        products.setProductPrice(dto.getProductPrice());
        productRepo.save(products);
        return productMapper.mapToDto(products);
    }
}