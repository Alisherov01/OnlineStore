package com.example.OnlineStore.repository;

import com.example.OnlineStore.entity.DiscountProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepo extends JpaRepository<DiscountProducts, Long> {
}

