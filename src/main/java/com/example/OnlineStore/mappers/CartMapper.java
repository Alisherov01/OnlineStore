package com.example.OnlineStore.mappers;

import com.example.OnlineStore.dto.CartDto;
import com.example.OnlineStore.entity.Cart;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CartMapper {
//    private final ModelMapper modelMapper;
//
//    public CartMapper() {
//        this.modelMapper = new ModelMapper();
//    }
//
//    public CartDto mapToDto(Cart cart) {
//        return modelMapper.map(cart, CartDto.class);
//    }
//
//    public Cart mapToModel(CartDto dto) {
//        return modelMapper.map(dto, Cart.class);
//    }
    CartDto mapToDto(Cart model);
}