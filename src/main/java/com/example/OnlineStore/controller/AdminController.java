package com.example.OnlineStore.controller;


import com.example.OnlineStore.dto.*;
import com.example.OnlineStore.entity.ResponseMessage;
import com.example.OnlineStore.enums.ResultCode;
import com.example.OnlineStore.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final DiscountService discountService;






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
            log.error("AdminController: create ", e);
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
            log.error("AdminController: getAll ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/api/removeCart/{id}")
    ResponseMessage<String> deleteCart(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    cartService.delete(id),
                    ResultCode.SUCCESS,
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("AdminController: deleteCart ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }


    //роуты для категорий
    @PostMapping("/api/create/category")
    public ResponseMessage<Long> createCategory(@RequestBody CategoriesDto dto) {
        try {
            return new ResponseMessage<>(
                    categoriesService.createCategory(dto),
                    ResultCode.SUCCESS,
                    "Категория успешно создана. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("AdminController: create ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/api/removeCategory/{id}")
    public ResponseMessage<String> deleteCategory(@PathVariable Long id) {
        try {
           return new ResponseMessage<>(
                   categoriesService.deleteCategory(id),
                   ResultCode.SUCCESS,
                   ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("AdminController: deleteCategory", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PutMapping("/api/categories/update/{id}")
    public ResponseMessage<CategoriesDto> update(@PathVariable Long id, @RequestBody CategoriesDto dto) {
        try {
            return new ResponseMessage<>(
                    categoriesService.update(id, dto),
                    ResultCode.SUCCESS,
                    "Парметры категорий успешно обновлены. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("AdminController: update", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }






    //роуты для заказов
    @GetMapping("/api/order/getAll")
    public ResponseMessage<OrderBillDto> getAllOrders() {
        try {
            return new ResponseMessage<>(
                    orderService.getAll(),
                    ResultCode.SUCCESS,
                    "Заказы успешно найдены. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("AdminController: getAllOrders ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PutMapping("/api/order/update")
    public ResponseMessage<OrderDto> update(@RequestParam Long id, @RequestBody OrderDto dto) {
        try {
            return new ResponseMessage<>(
                    orderService.update(id, dto),
                    ResultCode.SUCCESS,
                    "Параметры заказа успешно обновлены. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("AdminController: update", e);
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
                    "Параметры оплаты успешно обнавлены. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("AdminController: update ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("DELETE/payment/{id}")
    public ResponseMessage<String> delete(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    paymentService.delete(id),
                    ResultCode.SUCCESS,
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("AdminController: delete ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }






    //роуты для Одежды
    @DeleteMapping("/api/products/remove/{id}")
    public ResponseMessage<String> deleteProduct(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    productService.deleteProduct(id),
                    ResultCode.SUCCESS,
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("AdminController: deleteProduct ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PostMapping("/api/create/product")
    public ResponseMessage<Long> createProduct(@RequestBody ProductDto dto, @RequestParam Long categoryId) {
        try {
            return new ResponseMessage<>(
                    productService.createProduct(dto, categoryId),
                    ResultCode.SUCCESS,
                    "Одежда успешно создана. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("AdminController: create ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PutMapping("/api/product/update/{id}")
    public ResponseMessage<ProductDto> update(@PathVariable Long id, @RequestBody ProductDto dto) {
        try {
            return new ResponseMessage<>(
                    productService.update(id, dto),
                    ResultCode.SUCCESS,
                    "Параметры одежды успешно обновлены. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("AdminController: update", e);
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
            log.error("AdminController: getAll ", e);
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
            log.error("AdminController:  getById", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }


    @DeleteMapping("/api/user/remove/{id}")
    public ResponseMessage<String> deleteUsers(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    usersService.deleteUsers(id),
                    ResultCode.SUCCESS,
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("AdminController:  deleteUsers", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }





    //роуты для одежды с процентами

    @PostMapping("/api/productWithDiscount/create")
    public ResponseMessage<Long> createProductWithDiscount(@RequestBody DiscountDto dto) {
        try {
            return new ResponseMessage<>(
                    discountService.createProductWithDiscount(dto),
                    ResultCode.SUCCESS,
                    "Одежда успешно создана. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PaymentService: create ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/api/removeProductWithDiscount/{id}")
    void deleteProductWithDiscount(@PathVariable Long id) {
        categoriesService.deleteCategory(id);
    }

}
