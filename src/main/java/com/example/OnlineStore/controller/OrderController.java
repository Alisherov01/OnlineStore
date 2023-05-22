package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.OrderDto;
import com.example.OnlineStore.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class OrderController {
    OrderService service;

    @GetMapping("/order/{id}")
    public OrderDto getById(@PathVariable Long id) throws Exception {
        return service.getById(id);
    }

    @PostMapping("/order")
    public Long save(@RequestBody OrderDto dto){
        return service.save(dto);
    }
}
