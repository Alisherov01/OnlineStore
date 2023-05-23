package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.OrderDto;
import com.example.OnlineStore.entity.ResponseMessage;
import com.example.OnlineStore.enums.ResultCode;
import com.example.OnlineStore.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class OrderController {
    OrderService service;

    @GetMapping("/api/order/{id}")
    public ResponseMessage<OrderDto> getById(@PathVariable Long id) {
        try{
            return new ResponseMessage<>(
                    service.getById(id),
                    ResultCode.SUCCESS,
                    "Заказ успешно найден. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("OrderController: getById ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PostMapping("/api/order")
    public ResponseMessage<Long> save(@RequestBody OrderDto dto) {
        try {
            return new ResponseMessage<>(
                    service.save(dto),
                    ResultCode.SUCCESS,
                    "Заказ успешно создан.",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("OrderController: save ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }
}
