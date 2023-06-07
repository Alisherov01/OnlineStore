package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.DiscountProductDto;
import com.example.OnlineStore.entity.DiscountProducts;
import com.example.OnlineStore.repository.DiscountProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DiscountProductService {
    DiscountProductRepo discountProductRepo;

    public List<DiscountProductDto> getAll() {
        List<DiscountProductDto> dtos = new ArrayList<>();
        List<DiscountProducts> discountProducts = discountProductRepo.findAll();
        for(DiscountProducts p : discountProducts) {
            DiscountProductDto dto = new DiscountProductDto();
            dto.setProductName(p.getProductName());
            dto.setProductName(p.getProductName());
            dto.setProductName(p.getProductName());
            dto.setProductName(p.getProductName());
            dto.setProductName(p.getProductName());
            dto.setProductName(p.getProductName());
        }
        return null;
    }
}

//    public List<PaymentDto> getAll() {
//        List<PaymentDto> dtos = new ArrayList<>();
//        List<Payment> payments = paymentRepo.findAll();
//        for(Payment p : payments) {
//            PaymentDto dto = new PaymentDto();
//            dto.setTime(p.getTime());
//            dto.setOrderSum(p.getOrderSum());
//            dtos.add(dto);
//        }
//        return dtos;
//    }
