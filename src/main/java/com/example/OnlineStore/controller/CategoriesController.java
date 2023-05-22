package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.CategoriesDto;
import com.example.OnlineStore.service.CategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CategoriesController {
    CategoriesService categoriesService;

    @GetMapping("/categories")
    public List<CategoriesDto> getAll() {
        return categoriesService.getAll();
    }

}
