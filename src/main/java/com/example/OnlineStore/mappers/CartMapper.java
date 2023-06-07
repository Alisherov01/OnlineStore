package com.example.OnlineStore.mappers;

import com.example.OnlineStore.dto.CartDto;
import com.example.OnlineStore.entity.Cart;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    private final ModelMapper modelMapper;

    public CartMapper() {
        this.modelMapper = new ModelMapper();
    }

    public CartDto mapToDto(Cart cart) {
        return modelMapper.map(cart, CartDto.class);
    }
}