package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.PaymentDto;
import com.example.OnlineStore.entity.Payment;
import com.example.OnlineStore.repository.PaymentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentService {
    PaymentRepo paymentRepo;

    public List<PaymentDto> getAll() {
        List<PaymentDto> dtos = new ArrayList<>();
        List<Payment> payments = paymentRepo.findAll();
        for(Payment p : payments) {
            PaymentDto dto = new PaymentDto();
            dto.setTime(p.getTime());
            dto.setOrderSum(p.getOrderSum());
            dtos.add(dto);
        }
        return dtos;
    }

    public PaymentDto getById(Long id) throws Exception {
        Optional<Payment> payment = paymentRepo.findById(id);
        PaymentDto dto = new PaymentDto();
        if(payment.isPresent()) {
            dto.setTime(payment.get().getTime());
            dto.setOrderSum(payment.get().getOrderSum());
        } else {
            throw new Exception("Платежа с такимим данными не существует.");
        }
        return dto;
    }

    public Long create(PaymentDto dto) {
        Payment payment = new Payment();
        dto.setTime(payment.getTime());
        dto.setOrderSum(payment.getOrderSum());
        return paymentRepo.save(payment).getId();
    }

    public PaymentDto update(Long id) throws Exception {
        Payment payment = paymentRepo.findById(id).orElseThrow(() ->
                new Exception("Платежа с такимим данными не существует."));
        PaymentDto dto = new PaymentDto();
        dto.setTime(payment.getTime());
        dto.setOrderSum(payment.getOrderSum());
        return dto;
    }

    public void delete(Long id) {
        paymentRepo.deleteById(id);
    }
}
