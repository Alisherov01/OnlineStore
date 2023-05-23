package com.example.OnlineStore.repository;

import com.example.OnlineStore.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriesRepo extends JpaRepository<Categories, Long> {
}
