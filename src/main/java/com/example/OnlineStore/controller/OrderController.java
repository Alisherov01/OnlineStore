package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.OrderBillDto;
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
@RequestMapping("/auth")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/api/order/{id}")
    public ResponseMessage<OrderBillDto> getById(@PathVariable Long id) {
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
    ResponseMessage<String> deleteOrder(@PathVariable Long id){
        try {
            return new ResponseMessage<>(
                    orderService.deleteOrder(id),
                    ResultCode.SUCCESS,
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("OrderController: deleteOrder ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PostMapping("/api/order/add/product/{productId}/{orderId}")
    ResponseMessage<OrderDto> addProductInOrder(@PathVariable Long productId, @PathVariable Long orderId) {
        try {
            return new ResponseMessage<>(
                    orderService.addProductToOrder(productId, orderId),
                    ResultCode.SUCCESS,
                    "Продукт успешно добавлен в заказ. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("OrderController: addProductInOrder", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/api/order/remove/product/{productId}/{orderId}")
    ResponseMessage<OrderDto> removeProductInOrder(@PathVariable Long productId, @PathVariable Long orderId) {
        try {
            return new ResponseMessage<>(
                    orderService.removeProductFromOrder(productId, orderId),
                    ResultCode.SUCCESS,
                    "Продукт успешно удалён из заказа. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("OrderController: removeProductInOrder", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }
}
