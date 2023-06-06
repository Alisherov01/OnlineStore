package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.CartDto;
import com.example.OnlineStore.entity.Cart;
import com.example.OnlineStore.entity.Products;
import com.example.OnlineStore.mappers.CartMapper;
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
    CartMapper cartMapper;



    public List<CartDto> getAll() {
        List<CartDto> dtos = new ArrayList<>();
        List<Cart> carts = cartRepo.findAll();
        for (Cart p : carts) {
            CartDto dto = new CartDto();
            dto.setId(p.getId());
            dto.setProducts(p.getProducts());
            dtos.add(dto);
        }
        return dtos;
    }

    public CartDto getById(Long id) throws Exception {
        Optional<Cart> carts = cartRepo.findById(id);
        CartDto dto = new CartDto();
        if (carts.isPresent()) {
            dto.setId(carts.get().getId());
            dto.setProducts(carts.get().getProducts());
        } else {
            throw new Exception("Карзины с такими данными не существует");
        }
        return dto;
    }

    public Long saveInCart(CartDto dto) {
        Cart newCart = new Cart();
        newCart.setProducts(dto.getProducts());
        newCart = cartRepo.save(newCart);
        return newCart.getId();
    }


    public void delete(Long id) {
        cartRepo.deleteById(id);
    }

    public CartDto removeFromCart(Long id) throws Exception {
        Cart cart = cartRepo.findById(id).orElseThrow(() ->
                new Exception("Карзины с такими данными не существует"));
        if (cart.getProducts() != null) {
            delete(id);
        } else {
            return cartMapper.mapToDto(cart);
        }
        return null;
    }

    public Long create(CartDto dto) {
        Cart cart = new Cart();
        cart.setId(dto.getId());
        return cartRepo.save(cart).getId();
    }
}