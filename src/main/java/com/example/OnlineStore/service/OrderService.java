package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.OrderBillDto;
import com.example.OnlineStore.dto.OrderDto;
import com.example.OnlineStore.entity.Orders;
import com.example.OnlineStore.entity.Products;
import com.example.OnlineStore.mappers.OrderMapper;
import com.example.OnlineStore.repository.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final  OrderRepo orderRepo;
    private final OrderMapper orderMapper;

    public OrderBillDto getAll() {
        List<Orders> orders = orderRepo.findAll();
        List<OrderDto> orderDtoList = orders.stream().map(orderMapper::mapToDto).collect(Collectors.toList());
        BigDecimal sum = BigDecimal.ZERO;
        for (OrderDto orderDto : orderDtoList) {
            for (Products products : orderDto.getProducts()) {
                sum = sum.add(products.getProductPrice());
            }
        }
        OrderBillDto orderBillDto = new OrderBillDto();
        orderBillDto.setProductDtoList(orderDtoList);
        orderBillDto.setOrderSum(sum);
        return orderBillDto;
    }

    public OrderBillDto getById(Long id) throws Exception {
        Optional<Orders> orders = orderRepo.findById(id);
        List<OrderDto> orderDtoList = orders.stream().map(orderMapper::mapToDto).collect(Collectors.toList());
        BigDecimal sum = BigDecimal.ZERO;
        for (OrderDto orderDto : orderDtoList) {
            for (Products products : orderDto.getProducts()) {
                sum = sum.add(products.getProductPrice());
            }
        }
        OrderBillDto orderBillDto = new OrderBillDto();
        orderBillDto.setProductDtoList(orderDtoList);
        orderBillDto.setOrderSum(sum);
        return orderBillDto;
    }

    public Long save(OrderDto dto) {
        Orders orders = new Orders();
        orders.setFullName(dto.getFullName());
        orders.setAddress(dto.getAddress());
        orders.setOrderTime(LocalDate.now());
        return orderRepo.save(orders).getId();
    }

    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }

    public OrderDto update(Long id, OrderDto dto) throws Exception {
        Orders orders = orderRepo.findById(id).orElseThrow(() ->
                new Exception("Заказа с такими не существует. "));
        orders.setFullName(dto.getFullName());
        orders.setAddress(dto.getAddress());
        orders.setOrderTime(LocalDate.now());
        orderRepo.save(orders);
        return orderMapper.mapToDto(orders);
    }

}
