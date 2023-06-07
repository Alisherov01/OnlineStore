package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.CartDto;
import com.example.OnlineStore.dto.DiscountDto;
import com.example.OnlineStore.entity.Cart;
import com.example.OnlineStore.entity.DiscountProducts;
import com.example.OnlineStore.mappers.DiscountMapper;
import com.example.OnlineStore.repository.DiscountRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DiscountService {
    private final DiscountRepo discountRepo;

    public List<DiscountDto> getAll() {
        List<DiscountDto> dtos = new ArrayList<>();
        List<DiscountProducts> discountProducts = discountRepo.findAll();
        for (DiscountProducts p : discountProducts) {
            DiscountDto dto = new DiscountDto();
            dto.setProductName(p.getProductName());
            dto.setBrand(p.getBrand());
            dto.setProductType(p.getProductType());
            dto.setSumDiscount(p.getSumDiscount());
            dto.setProductPrice(p.getProductPrice());
            dto.setProductWithDiscount((p.getProductPrice() * p.getSumDiscount())/100);
            dtos.add(dto);
        }
        return dtos;
    }

    public DiscountDto getById(Long id) throws Exception {
        Optional<DiscountProducts> discountProducts = discountRepo.findById(id);
        DiscountDto dto = new DiscountDto();
        if (discountProducts.isPresent()) {
            dto.setProductName(discountProducts.get().getProductName());
            dto.setBrand(discountProducts.get().getBrand());
            dto.setProductType(discountProducts.get().getProductType());
            dto.setSumDiscount(discountProducts.get().getSumDiscount());
            dto.setProductPrice(discountProducts.get().getProductPrice());
            dto.setProductWithDiscount((discountProducts.get().getProductPrice() * discountProducts.get().getSumDiscount())/100);
        } else {
            throw new Exception("Продукта с такими данными не существует");
        }
        return dto;
    }

    public Long createProductWithDiscount (DiscountDto dto){
        DiscountProducts discountProducts = new DiscountProducts();
        discountProducts.setProductName(dto.getProductName());
        discountProducts.setBrand(dto.getBrand());
        discountProducts.setProductType(dto.getProductType());
        discountProducts.setSumDiscount(dto.getSumDiscount());
        discountProducts.setProductPrice(dto.getProductPrice());
        discountProducts.setProductWithDiscount((dto.getProductPrice() * dto.getSumDiscount())/100);
        return discountRepo.save(discountProducts).getId();
    }

    public void delete(Long id) {
        discountRepo.deleteById(id);
    }
}
