package com.example.OnlineStore.repository;

import com.example.OnlineStore.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardRepo extends JpaRepository<Card, Long> {
    @Query(value = "select * from card where card.cvvcode = ?" ,nativeQuery = true)
    Card findCardByCVVCode(Integer CVVCode);
}