package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.CartDto;
import com.example.OnlineStore.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CartController {
    CartService service;

    @GetMapping("/api/cart")
    public List<CartDto> getAll() {
        return service.getAll();
    }
}
