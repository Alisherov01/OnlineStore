package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.CategoriesDto;
import com.example.OnlineStore.entity.Categories;
import com.example.OnlineStore.entity.Products;
import com.example.OnlineStore.mappers.CategoriesMapper;
import com.example.OnlineStore.repository.CategoriesRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoriesService {
    CategoriesRepo categoriesRepo;

    CategoriesMapper categoriesMapper;

    public List<CategoriesDto> getAll() {
        List<CategoriesDto> dtos = new ArrayList<>();
        List<Categories> categories = categoriesRepo.findAll();
        for (Categories p : categories) {
            CategoriesDto dto = new CategoriesDto();
            dto.setCategoryName(p.getCategoryName());
            dto.setProductAmount(p.getProductAmount());
            dtos.add(dto);
        }
        return dtos;
    }


    public CategoriesDto getById(Long id) throws Exception {
        Optional<Categories> categories = categoriesRepo.findById(id);
        CategoriesDto dto = new CategoriesDto();
        if (categories.isPresent()) {
            dto.setCategoryName(categories.get().getCategoryName());
            dto.setProductAmount(categories.get().getProductAmount());
        } else {
            throw new Exception("Категории с такими данными не существует");
        }
        return dto;
    }

}
