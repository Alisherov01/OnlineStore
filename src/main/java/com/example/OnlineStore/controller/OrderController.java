package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.OrderDto;
import com.example.OnlineStore.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class OrderController {
    OrderService service;

    @GetMapping("/api/order/{id}")
    public OrderDto getById(@PathVariable Long id) throws Exception {
        return service.getById(id);
    }

    @PostMapping("/api/order")
    public Long save(@RequestBody OrderDto dto){
        return service.save(dto);
    }
}
