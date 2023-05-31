package com.example.OnlineStore.controller;


import com.example.OnlineStore.service.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UsersService usersService;

    private final CardService cardService;

    private final CategoriesService categoriesService;

    private final OrderService orderService;

    private final ProductService productService;



}
