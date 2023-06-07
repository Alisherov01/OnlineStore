package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.DiscountDto;
import com.example.OnlineStore.entity.ResponseMessage;
import com.example.OnlineStore.enums.ResultCode;
import com.example.OnlineStore.service.DiscountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class DiscountProductController {
    private final DiscountService discountService;

    @GetMapping("/api/productWithDiscount/{id}")
    public ResponseMessage<DiscountDto> getById(@PathVariable Long id){
        try {
            return new ResponseMessage<>(
                    discountService.getById(id),
                    ResultCode.SUCCESS,
                    "Одежда успешно найдена. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CartController: getById ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }


    @GetMapping("/api/productWithDiscount/getAll")
    public ResponseMessage<List<DiscountDto>> getAllCart() {
        try {
            return new ResponseMessage<>(
                    discountService.getAll(),
                    ResultCode.SUCCESS,
                    "Одежды успешно найдены. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CartController: getAll ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

}
