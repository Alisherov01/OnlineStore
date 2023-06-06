package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.OrderDto;
import com.example.OnlineStore.dto.ProductDto;
import com.example.OnlineStore.entity.Orders;
import com.example.OnlineStore.mappers.OrderMapper;
import com.example.OnlineStore.repository.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    OrderRepo orderRepo;
    OrderMapper orderMapper;

    public List<OrderDto> getAll() {
        List<OrderDto> dtos = new ArrayList<>();
        List<Orders> orders = orderRepo.findAll();
        for (Orders p : orders) {
            OrderDto dto = new OrderDto();
            dto.setOrderTime(p.getOrderTime());
            dtos.add(dto);
        }
        return dtos;
    }

    public OrderDto getById(Long id) throws Exception {
        Optional<Orders> orders = orderRepo.findById(id);
        OrderDto dto = new OrderDto();
        if (orders.isPresent()) {
            dto.setFullName(orders.get().getFullName());
            dto.setAddress(orders.get().getAddress());
            dto.setOrderTime(LocalDate.now());
            dto.setOrderSum(orders.get().getOrderSum());
            dto.setProducts(orders.get().getProducts());
        } else {
            throw new Exception("Заказа с такими данными не существует");
        }
        return dto;
    }

    public Long save(OrderDto dto) {
        Orders orders = new Orders();
        dto.setFullName(orders.getFullName());
        dto.setAddress(orders.getAddress());
        dto.setOrderTime(LocalDate.now());
        dto.setOrderSum(orders.getOrderSum());
        dto.setProducts(orders.getProducts());
        return orderRepo.save(orders).getId();
    }

    public void deleteOrder(Long id){
        orderRepo.deleteById(id);
    }

    public OrderDto update(Long id, OrderDto dto) throws Exception {
        Orders orders = orderRepo.findById(id).orElseThrow(() ->
                new Exception("Заказа с такими не существует. "));
        orders.setFullName(dto.getFullName());
        orders.setAddress(dto.getAddress());
        orders.setOrderTime(LocalDate.now());
        orders.setOrderSum(dto.getOrderSum());
        orderRepo.save(orders);
        return orderMapper.mapToDto(orders);
    }

}
