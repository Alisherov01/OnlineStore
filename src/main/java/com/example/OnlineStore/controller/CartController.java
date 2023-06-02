package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.CartDto;
import com.example.OnlineStore.entity.ResponseMessage;
import com.example.OnlineStore.enums.ResultCode;
import com.example.OnlineStore.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CartController {
    CartService cartService;

    @GetMapping("/api/cart/{id}")
    public ResponseMessage<CartDto> getById(@PathVariable Long id){
        try {
            return new ResponseMessage<>(
                    cartService.getById(id),
                    ResultCode.SUCCESS,
                    "Корзина успешно найдена. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CartController: getById ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PostMapping("/api/cart/add")
    public ResponseMessage<Long> addProductInCart(@RequestBody CartDto dto) {
        try {
            return new ResponseMessage<>(
                    cartService.saveInCart(dto),
                    ResultCode.SUCCESS,
                    "Продукт успешно добавлен в корзину. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CartController: addProductInCart ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/api/cart/remove/{id}")
    public ResponseMessage<CartDto> removeOrderFromCart(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    cartService.removeFromCart(id),
                    ResultCode.SUCCESS,
                    "Продукт успешно удалён из корзины. ",
                    ResultCode.FAIL.getHttpCode());
        } catch (Exception e) {
            log.error("CartController: removeOrderFromCart ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }
}
