package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.ProductDto;
import com.example.OnlineStore.entity.ResponseMessage;
import com.example.OnlineStore.enums.ResultCode;
import com.example.OnlineStore.mappers.ProductMapper;
import com.example.OnlineStore.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class ProductController {
    private final ProductService service;

    @GetMapping("/api/products")
    public ResponseMessage<List<ProductDto>> getAll() {
        try {
            return new ResponseMessage<>(
                    service.getAll(),
                    ResultCode.SUCCESS,
                    "Одежды успешно найдены. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("ProductController: getAll ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/api/products/{id}")
    public ResponseMessage<ProductDto> getById(@PathVariable Long id) {
        try{
            return new ResponseMessage<>(
                    service.getById(id),
                    ResultCode.SUCCESS,
                    "Одежда успешно найдена. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("ProductController: getById ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }
}
