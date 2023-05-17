package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.PaymentDto;
import com.example.OnlineStore.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PaymentController {
    PaymentService service;

    @PostMapping("POST /payment")
    public Long create(@RequestBody PaymentDto dto) {
        return service.create(dto);
    }

    @GetMapping("GET /payment/{id}")
    public PaymentDto getById(@PathVariable Long id) throws Exception {
        return service.getById(id);
    }

    @PutMapping("PUT /payment/{id}")
    public PaymentDto update(@PathVariable Long id) {
        return service.update(id);
    }

    @DeleteMapping("DELETE /payment/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
