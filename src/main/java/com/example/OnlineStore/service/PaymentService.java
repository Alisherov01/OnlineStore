package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.PaymentDto;
import com.example.OnlineStore.entity.Payment;
import com.example.OnlineStore.mappers.PaymentMapper;
import com.example.OnlineStore.repository.PaymentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepo paymentRepo;
    private final PaymentMapper paymentMapper;

    public List<PaymentDto> getAll() {
        List<PaymentDto> dtos = new ArrayList<>();
        List<Payment> payments = paymentRepo.findAll();
        for (Payment p : payments) {
            PaymentDto dto = new PaymentDto();
            dto.setUsers(p.getUsers());
            dto.setTime(LocalDate.now());
            dtos.add(dto);
        }
        return dtos;
    }

    public PaymentDto getById(Long id) throws Exception {
        Optional<Payment> payment = paymentRepo.findById(id);
        PaymentDto dto = new PaymentDto();
        if (payment.isPresent()) {
            dto.setUsers(payment.get().getUsers());
            dto.setTime(LocalDate.now());
        } else {
            throw new Exception("Платежа с такимим данными не существует.");
        }
        return dto;
    }

    public Long create(PaymentDto dto) {
        Payment payment = new Payment();
        payment.setUsers(dto.getUsers());
        payment.setTime(LocalDate.now());
        return paymentRepo.save(payment).getId();
    }

    public PaymentDto update(Long id, PaymentDto dto) throws Exception {
        Payment payment = paymentRepo.findById(id).orElseThrow(() ->
                new Exception("Платежа с такимим данными не существует."));
        payment.setUsers(dto.getUsers());
        payment.setTime(LocalDate.now());
        return paymentMapper.mapToDto(paymentRepo.save(payment));
    }

        public String delete(Long id) {
            paymentRepo.deleteById(id);
            return "Оплата успешно отменена. ";
        }
    }
