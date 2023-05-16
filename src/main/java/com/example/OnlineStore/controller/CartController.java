package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.CartDto;
import com.example.OnlineStore.entity.Cart;
import com.example.OnlineStore.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CartController {
    CartService cartService;

    @GetMapping("/api/cart")
    public List<CartDto> getAll() {
        return cartService.getAll();
    }

    @PostMapping("/api/cart/add")
    public Long addProductInCart(@RequestBody CartDto dto){
       return cartService.saveInCart(dto);
    }

    @DeleteMapping("/api/cart/remove/{id}")
    public CartDto removeOrderFromCart(@PathVariable Long id){
       return cartService.removeFromCart(id);
    }

    @DeleteMapping("/api/remove/{id}")
    void delete(@PathVariable Long id){
        cartService.delete(id);
    }

}
