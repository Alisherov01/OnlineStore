package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.OrderDto;
import com.example.OnlineStore.entity.Orders;
import com.example.OnlineStore.repository.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    OrderRepo orderRepo;

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
            dto.setOrderTime(orders.get().getOrderTime());
        } else {
            throw new Exception("Заказа с такими данными не существует");
        }
        return dto;
    }

    public Long save(OrderDto dto) {
        Orders orders = new Orders();
        orders.setOrderTime(dto.getOrderTime());
        return orderRepo.save(orders).getId();
    }

    public void deleteOrder(Long id){
        orderRepo.deleteById(id);
    }
}
