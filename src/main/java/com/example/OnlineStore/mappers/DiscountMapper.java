package com.example.OnlineStore.mappers;

import com.example.OnlineStore.dto.DiscountDto;
import com.example.OnlineStore.entity.Categories;
import com.example.OnlineStore.entity.DiscountProducts;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DiscountMapper {
    private final ModelMapper modelMapper;

    public DiscountMapper() {
        this.modelMapper = new ModelMapper();
    }

    public DiscountDto mapToDto(DiscountProducts discountProducts) {
        return modelMapper.map(discountProducts, DiscountDto.class);
    }
}
