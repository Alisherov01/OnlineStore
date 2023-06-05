package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.CartDto;
import com.example.OnlineStore.entity.Cart;
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
            dto.setOrderPrice(p.getOrderPrice());
            dto.setProductAmount(p.getProductAmount());
            dto.setOrdersSum(p.getOrdersSum());
            if (p.getOrderPrice() == 1) {
                dto.setOrdersSum(p.getOrderPrice());
            } else {
                dto.setOrdersSum(p.getOrderPrice() * p.getProductAmount());
            }
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
            dto.setProductAmount(carts.get().getProductAmount());
            dto.setProducts(carts.get().getProducts());
        } else {
            throw new Exception("Карзины с такими данными не существует");
        }
        return dto;
    }

    public Long saveInCart(CartDto dto) {

        Cart newCart = new Cart();
        newCart.setOrderPrice(dto.getOrderPrice());
        newCart.setProductAmount(dto.getProductAmount());

        newCart = cartRepo.save(newCart);
        return newCart.getId();
    }


    public void delete(Long id) {
        cartRepo.deleteById(id);
    }

    public CartDto removeFromCart(Long id) throws Exception {
        Cart cart = cartRepo.findById(id).orElseThrow(() ->
                new Exception("Карзины с такими данными не существует"));
        ;
        if (cart.getProductAmount() == 0) {
            delete(id);
        } else {
            cart.setProductAmount(cart.getProductAmount() - 1);
            return cartMapper.mapToDto(cartRepo.save(cart));
        }
        return null;
    }

    public Long create(CartDto dto) {
        Cart cart = new Cart();
        cart.setOrderPrice(dto.getOrderPrice());
        cart.setProducts(dto.getProducts());
        cart.setOrdersSum(dto.getOrdersSum());
        return cartRepo.save(cart).getId();
    }
}