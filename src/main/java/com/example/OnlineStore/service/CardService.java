package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.CardDto;
import com.example.OnlineStore.entity.Card;
import com.example.OnlineStore.entity.Orders;
import com.example.OnlineStore.entity.Products;
import com.example.OnlineStore.repository.CardRepo;
import com.example.OnlineStore.repository.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CardService {
    private final CardRepo cardRepo;
    private final OrderRepo orderRepo;


    public Long save(CardDto dto) {
        Card card = new Card();
        card.setCardName(dto.getCardName());
        card.setCartNumber(dto.getCartNumber());
        card.setCVVCode(dto.getCVVCode());
        card.setCardBalance(dto.getCardBalance());
        return cardRepo.save(card).getId();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BigDecimal payWithCard(Integer CVVCode, Long orderId) throws Exception {
        Orders orders = orderRepo.findById(orderId).orElseThrow(()
                -> new Exception("Нет заказа с такими днными."));

        double orderSum = orders.getProducts().stream()
                .map(Products::getProductPrice)
                .mapToDouble(BigDecimal::doubleValue).sum();

        Card card = cardRepo.findCardByCVVCode(CVVCode);
        if (orderSum < card.getCardBalance().doubleValue()) {
            BigDecimal res = card.getCardBalance().subtract(new BigDecimal(orderSum));
            card.setCardBalance(res);
            cardRepo.save(card);
            return card.getCardBalance();
        } else {
            throw new Exception("У вас не достаточна средств.");
        }
    }

    public String delete(Long id) {
        cardRepo.deleteById(id);
        return "Карта усрешно удалена. ";
    }
}