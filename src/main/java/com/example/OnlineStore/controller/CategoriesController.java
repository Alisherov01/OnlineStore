package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.CategoriesDto;
import com.example.OnlineStore.dto.ProductDto;
import com.example.OnlineStore.entity.ResponseMessage;
import com.example.OnlineStore.enums.ResultCode;
import com.example.OnlineStore.service.CategoriesService;
import com.example.OnlineStore.service.ProductService;
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
public class CategoriesController {
    CategoriesService categoriesService;
    ProductService productService;

    @GetMapping("/api/categories/{id}")
    public ResponseMessage<CategoriesDto> getById(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    categoriesService.getById(id),
                    ResultCode.SUCCESS,
                    "Категория успешно найдена. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CategoriesController:  getAll", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/api/product/categories/{id}")
    public ResponseMessage<List<ProductDto>> getProductsByCategoryId(@PathVariable Long id){
        try {
            return new ResponseMessage<>(
                    productService.getProductsByCategoryId(id),
                    ResultCode.SUCCESS,
                    "Продукт успешно найден. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CategoriesController: getProductsByCategoryId ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }
}
