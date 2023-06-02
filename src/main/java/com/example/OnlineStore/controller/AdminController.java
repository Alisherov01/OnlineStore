package com.example.OnlineStore.controller;


import com.example.OnlineStore.dto.*;
import com.example.OnlineStore.entity.ResponseMessage;
import com.example.OnlineStore.enums.ResultCode;
import com.example.OnlineStore.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    private final PaymentService paymentService;

    private final UsersService usersService;

    private final CategoriesService categoriesService;

    private final OrderService orderService;

    private final ProductService productService;

    private final CartService cartService;


    //роуты для корзины
    @PostMapping("/api/create/cart")
    public ResponseMessage<Long> createCart(@RequestBody CartDto dto) {
        try {
            return new ResponseMessage<>(
                    cartService.create(dto),
                    ResultCode.SUCCESS,
                    "Карзина успешно создана. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PaymentService: create ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/api/cart")
    public ResponseMessage<List<CartDto>> getAllCart() {
        try {
            return new ResponseMessage<>(
                    cartService.getAll(),
                    ResultCode.SUCCESS,
                    "Корзина успешно найдена. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CartController: getAll ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/api/removeCart/{id}")
    void deleteCart(@PathVariable Long id) {
        cartService.delete(id);
    }


    //роуты дл категорий
    @GetMapping("/api/categories")
    public ResponseMessage<List<CategoriesDto>> getAllCategories() {
        try {
            return new ResponseMessage<>(
                    categoriesService.getAll(),
                    ResultCode.SUCCESS,
                    "Категории успешно найдены. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CategoriesController:  getAll", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PostMapping("/api/create/category")
    public ResponseMessage<Long> createCategory(@RequestBody CategoriesDto dto) {
        try {
            return new ResponseMessage<>(
                    categoriesService.createCategory(dto),
                    ResultCode.SUCCESS,
                    "Категория успешно создана. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PaymentService: create ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/api/removeCategory/{id}")
    void deleteCategory(@PathVariable Long id) {
        categoriesService.deleteCategory(id);
    }


    //роуты для заказов
    @GetMapping("/api/order/getAll")
    public ResponseMessage<List<OrderDto>> getAllOOrders() {
        try {
            return new ResponseMessage<>(
                    orderService.getAll(),
                    ResultCode.SUCCESS,
                    "Заказы успешно найдены. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("OrderController: getById ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }


    //роуты для оплаты
    @PutMapping("PUT/payment/{id}")
    public ResponseMessage<PaymentDto> update(@PathVariable Long id, @RequestBody PaymentDto dto) {
        try {
            return new ResponseMessage<>(
                    paymentService.update(id, dto),
                    ResultCode.SUCCESS,
                    "Оплата успешно обнавлена. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PaymentService: update ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("DELETE/payment/{id}")
    public void delete(@PathVariable Long id) {
        paymentService.delete(id);
    }


    //роут для продуктов
    @DeleteMapping("/api/products/remove/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/api/create/product")
    public ResponseMessage<Long> createProduct(@RequestBody ProductDto dto) {
        try {
            return new ResponseMessage<>(
                    productService.createProduct(dto),
                    ResultCode.SUCCESS,
                    "Продукт успешно создан. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PaymentService: create ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    // роуты для юзеров

    @GetMapping("/api/getAllUsers")
    public ResponseMessage<List<UserDto>> getAllUsers() {
        try {
            return new ResponseMessage<>(
                    usersService.getAllUsers(),
                    ResultCode.SUCCESS,
                    "Пользователи успешно найдены. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("ProductController: getAll ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/api/user/{id}")
    public ResponseMessage<UserDto> getById(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    usersService.getById(id),
                    ResultCode.SUCCESS,
                    "Пользователь успешно найден. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CategoriesController:  getById", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }


    @DeleteMapping("/api/user/remove/{id}")
    public void deleteUsers(@PathVariable Long id) {
        usersService.deleteUsers(id);
    }

}
