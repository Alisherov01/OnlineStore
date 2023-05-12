package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.CategoriesDto;
import com.example.OnlineStore.service.CategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoriesController {
    CategoriesService service;

    @GetMapping("/api/categories")
    public List<CategoriesDto> getAll() {
        return service.getAll();
    }
}
