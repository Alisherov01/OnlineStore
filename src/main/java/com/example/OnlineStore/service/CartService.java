package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.CartDto;
import com.example.OnlineStore.entity.Cart;
import com.example.OnlineStore.repository.CartRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {
    CartRepo cartRepo;


    public List<CartDto> getAll() {
        List<CartDto> dtos = new ArrayList<>();
        List<Cart> carts = cartRepo.findAll();
        for (Cart p : carts) {
            CartDto dto = new CartDto();
            dto.setOrdersSum(p.getOrdersSum());
            dto.setDiscountSum(p.getDiscountSum());
            dto.setProductAmount(p.getProductAmount());
            dto.setProducts(p.getProducts());
            dtos.add(dto);
        }
        return dtos;
    }

    public CartDto getById(Long id) throws Exception {
        Optional<Cart> carts = cartRepo.findById(id);
        CartDto dto = new CartDto();
        if (carts.isPresent()) {
            dto.setOrdersSum(carts.get().getOrdersSum());
            dto.setDiscountSum(carts.get().getDiscountSum());
            dto.setProductAmount(carts.get().getProductAmount());
            dto.setProducts(carts.get().getProducts());
        } else {
            throw new Exception("Карзина с такими данными не существует");
        }
        return dto;
    }
}
