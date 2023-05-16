package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.CategoriesDto;
import com.example.OnlineStore.entity.Categories;
import com.example.OnlineStore.service.CategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoriesController {
    CategoriesService categoriesService;

    @GetMapping("/api/categories")
    public List<CategoriesDto> getAll() {
        return categoriesService.getAll();
    }

    @GetMapping("/api/categories/{id}/product")
    public Categories getProductsByCategoryId(@PathVariable Long id){
        return categoriesService.getProductsByCategoryId(id);
    }
}
