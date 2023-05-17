package com.example.OnlineStore.repository;

import com.example.OnlineStore.entity.Categories;
import com.example.OnlineStore.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepo extends JpaRepository<Categories, Long> {
}
