package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.PaymentDto;
import com.example.OnlineStore.entity.ResponseMessage;
import com.example.OnlineStore.enums.ResultCode;
import com.example.OnlineStore.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class PaymentController {
    PaymentService service;

    @PostMapping("/POST/payment")
    public ResponseMessage<Long> create(@RequestBody PaymentDto dto) {
        try {
            return new ResponseMessage<>(
                    service.create(dto),
                    ResultCode.SUCCESS,
                    "Оплата успешно сохранено. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PaymentService: create ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/GET/payment/{id}")
    public ResponseMessage<PaymentDto> getById(@PathVariable Long id) throws Exception {
        try {
            return new ResponseMessage<>(
                    service.getById(id),
                    ResultCode.SUCCESS,
                    "Оплата успешно найдено. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PaymentService: getById ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PutMapping("/PUT/payment/{id}")
    public ResponseMessage<PaymentDto> update(@PathVariable Long id) throws Exception {
        try {
            return new ResponseMessage<>(
                    service.update(id),
                    ResultCode.SUCCESS,
                    "Оплата успешно обнавлено. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PaymentService: update ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/DELETE/payment/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
