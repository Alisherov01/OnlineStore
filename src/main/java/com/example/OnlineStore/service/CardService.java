package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.CardDto;
import com.example.OnlineStore.entity.Card;
import com.example.OnlineStore.entity.Cart;
import com.example.OnlineStore.entity.Orders;
import com.example.OnlineStore.entity.Users;
import com.example.OnlineStore.mappers.CartMapper;
import com.example.OnlineStore.repository.CardRepo;
import com.example.OnlineStore.repository.CartRepo;
import com.example.OnlineStore.repository.OrderRepo;
import com.example.OnlineStore.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class CardService {
    CardRepo cardRepo;

    CartRepo cartRepo;

    CartMapper cartMapper;
    OrderRepo orderRepo;

    public Long save(CardDto dto) {
        Card card = new Card();
        card.setCardName(dto.getCardName());
        card.setCartNumber(dto.getCartNumber());
        card.setCVVCode(dto.getCVVCode());
        card.setCardBalance(dto.getCardBalance());
        return cardRepo.save(card).getId();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Integer payWithCard(String CVVCode, Long id) throws Exception {
        Orders order = orderRepo.findById(id).orElseThrow(()
                -> new Exception("Нет заказа с такими днными."));
        Card card = cardRepo.findCardByCVVCode(CVVCode);
        if (order.getOrderSum() < card.getCardBalance()) {
            Integer res = card.getCardBalance() - order.getOrderSum();
            card.setCardBalance(res);
            cardRepo.save(card);
            return card.getCardBalance();
        } else {
            throw new Exception("У вас не достаточна средств.");
        }
    }

    public void delete(Long id) {
        cardRepo.deleteById(id);
    }
}