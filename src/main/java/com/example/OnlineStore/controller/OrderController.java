package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.OrderDto;
import com.example.OnlineStore.entity.ResponseMessage;
import com.example.OnlineStore.enums.ResultCode;
import com.example.OnlineStore.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class OrderController {
    OrderService orderService;

    @GetMapping("/api/order/{id}")
    public ResponseMessage<OrderDto> getById(@PathVariable Long id) {
        try{
            return new ResponseMessage<>(
                    orderService.getById(id),
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
                    orderService.save(dto),
                    ResultCode.SUCCESS,
                    "Заказ успешно создан.",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("OrderController: save ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/api/removeOrder/{id}")
    void deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
    }
}
