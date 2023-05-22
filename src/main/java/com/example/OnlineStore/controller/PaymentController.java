package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.PaymentDto;
import com.example.OnlineStore.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PaymentController {
    PaymentService service;

    @PostMapping("payment")
    public Long create(@RequestBody PaymentDto dto) {
        return service.create(dto);
    }

    @GetMapping("payment/{id}")
    public PaymentDto getById(@PathVariable Long id) throws Exception {
        return service.getById(id);
    }

    @PutMapping("payment/{id}")
    public PaymentDto update(@PathVariable Long id) {
        return service.update(id);
    }

    @DeleteMapping("payment/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
