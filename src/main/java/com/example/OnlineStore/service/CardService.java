package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.CardDto;
import com.example.OnlineStore.dto.CartDto;
import com.example.OnlineStore.entity.Card;
import com.example.OnlineStore.entity.Cart;
import com.example.OnlineStore.mappers.CartMapper;
import com.example.OnlineStore.repository.CardRepo;
import com.example.OnlineStore.repository.CartRepo;
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

    public Long save(CardDto dto) {
        Card card = new Card();
        card.setFullName(dto.getFullName());
        card.setCartNumber(dto.getCartNumber());
        card.setCVVCode(dto.getCVVCode());
        card.setCardBalance(dto.getCardBalance());
        return cardRepo.save(card).getId();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Integer payWithCard(String CVVCode, Long id) throws Exception {
        Cart cart = cartRepo.findById(id).orElseThrow(()
                -> new Exception("Нет карзины с такими данными."));
        Card card = cardRepo.findCardByCVVCode(CVVCode);
        if(cart.getOrdersSum() < card.getCardBalance()) {
            Integer res = card.getCardBalance() - cart.getOrdersSum();
            card.setCardBalance(res);
            cardRepo.save(card);
            return card.getCardBalance();
        } else {
            throw new Exception("У вас не достаточна средств.");
        }
    }
}
