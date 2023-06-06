package com.example.OnlineStore.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderBillDto {
private List<OrderDto> productDtoList;
private BigDecimal orderSum;
}
