package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.CardDto;
import com.example.OnlineStore.dto.PaymentDto;
import com.example.OnlineStore.entity.ResponseMessage;
import com.example.OnlineStore.enums.ResultCode;
import com.example.OnlineStore.service.CardService;
import com.example.OnlineStore.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService service;
    private final CardService cardService;

    @PostMapping("POST/payment")
    public ResponseMessage<Long> create(@RequestBody PaymentDto dto) {
        try {
            return new ResponseMessage<>(
                    service.create(dto),
                    ResultCode.SUCCESS,
                    "Оплата успешно сохранена. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PaymentService: create ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("GET/payment/{id}")
    public ResponseMessage<PaymentDto> getById(@PathVariable Long id) throws Exception {
        try {
            return new ResponseMessage<>(
                    service.getById(id),
                    ResultCode.SUCCESS,
                    "Оплата успешно найдена. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PaymentService: getById ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PostMapping("/payment/card")
    public ResponseMessage<BigDecimal> payWithCard(@RequestParam Integer CVVCode, @RequestParam Long id) {
        try {
            return new ResponseMessage<>(
                    cardService.payWithCard(CVVCode, id),
                    ResultCode.SUCCESS,
                    "Оплата успешно выполнена.",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PaymentService: payWithCard", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PostMapping("/api/create/card")
    public ResponseMessage<Long> createCart(@RequestBody CardDto dto) {
        try {
            return new ResponseMessage<>(
                    cardService.save(dto),
                    ResultCode.SUCCESS,
                    "Карта успешно сохранена. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PaymentService: create ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/api/removeCard/{id}")
    public ResponseMessage<String> deleteCard(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    cardService.delete(id),
                    ResultCode.SUCCESS,
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PaymentService:deleteCategory ");
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }
}
