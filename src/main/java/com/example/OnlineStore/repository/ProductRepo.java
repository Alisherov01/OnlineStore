package com.example.OnlineStore.repository;

import com.example.OnlineStore.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Products,Long> {
    @Query(value = "select * from products where category_id = ?", nativeQuery = true)
    List<Products> getProductsByCategories(Long id);
}
