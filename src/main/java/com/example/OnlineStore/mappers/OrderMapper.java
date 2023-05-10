package com.example.OnlineStore.mappers;

import com.example.OnlineStore.dto.OrderDto;
import com.example.OnlineStore.entity.Orders;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class OrderMapper {
    private final ModelMapper modelMapper;

    public OrderMapper() {
        this.modelMapper = new ModelMapper();
    }

    public OrderDto mapToDto(Orders order) {
        return modelMapper.map(order, OrderDto.class);
    }

    public Orders mapToModel(OrderDto dto) {
        return modelMapper.map(dto, Orders.class);
    }
}