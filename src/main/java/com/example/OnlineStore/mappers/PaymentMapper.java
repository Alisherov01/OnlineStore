package com.example.OnlineStore.mappers;

import com.example.OnlineStore.dto.PaymentDto;
import com.example.OnlineStore.entity.Payment;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface PaymentMapper {
//    private final ModelMapper modelMapper;
//
//    public PaymentMapper() {
//        this.modelMapper = new ModelMapper();
//    }
//    public PaymentDto mapToDto(Payment payment) {
//        return modelMapper.map(payment, PaymentDto.class);
//    }

    Payment mapToDto(PaymentDto dto);
}