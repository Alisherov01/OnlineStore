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
            dto.setOrdersSum(p.getOrdersSum());
            if(p.getOrdersSum() == 1){
                dto.setOrdersSum(p.getOrdersSum());
            }else{
                dto.setOrdersSum(p.getOrdersSum() * p.getProductAmount());
            }
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
            dto.setProductAmount(carts.get().getProductAmount());
            dto.setProducts(carts.get().getProducts());
        } else {
            throw new Exception("Карзина с такими данными не существует");
        }
        return dto;
    }

    public Long saveInCart (CartDto dto) {

        Cart newCart = new Cart();
        newCart.setProductAmount(dto.getProductAmount());
        newCart.setOrdersSum(dto.getOrdersSum());

        newCart = cartRepo.save(newCart);
        return newCart.getId();
    }


    public void delete(Long id){
        cartRepo.deleteById(id);
    }

    public CartDto removeFromCart (Long id) throws Exception {
        Cart cart = cartRepo.findById(id).orElseThrow(() ->
                new Exception("Карзина с такими данными не существует"));;
        if(cart.getProductAmount() == 0){
            delete(id);
        }else {
            cart.setProductAmount(cart.getProductAmount() - 1);
            return cartMapper.mapToDto(cartRepo.save(cart));
        }
        return null;
    }
}
