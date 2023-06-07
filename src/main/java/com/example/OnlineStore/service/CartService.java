package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.CartDto;
import com.example.OnlineStore.entity.Cart;
import com.example.OnlineStore.entity.Products;
import com.example.OnlineStore.mappers.CartMapper;
import com.example.OnlineStore.repository.CartRepo;
import com.example.OnlineStore.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepo cartRepo;
    private final ProductRepo productRepository;
    private final CartMapper cartMapper;


    public List<CartDto> getAll() {
        List<CartDto> dtos = new ArrayList<>();
        List<Cart> carts = cartRepo.findAll();
        for (Cart p : carts) {
            CartDto dto = new CartDto();
            dto.setId(p.getId());
            dto.setProducts(p.getProducts());
            dtos.add(dto);
        }
        return dtos;
    }

    public CartDto getById(Long id) throws Exception {
        Optional<Cart> carts = cartRepo.findById(id);
        CartDto dto = new CartDto();
        if (carts.isPresent()) {
            dto.setId(carts.get().getId());
            dto.setProducts(carts.get().getProducts());
        } else {
            throw new Exception("Карзины с такими данными не существует");
        }
        return dto;
    }


    public CartDto saveInCart(long productId, long cartId) throws Exception {
        Products products = productRepository.findById(productId).orElseThrow(() -> new Exception("Продукта с такими данными нет"));
        Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new Exception("Карзины с такими данными не существует"));
        List<Products> productsList = cart.getProducts();
        productsList.add(products);
        var saveCart= cartRepo.save(cart);
        return convertToDto(saveCart);
    }
    public CartDto convertToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setProducts(cart.getProducts());
        return cartDto;
    }



    public void delete(Long id) {
        cartRepo.deleteById(id);
    }


    public CartDto removeFromCart(long productId, long cartId) throws Exception {
        Products products = productRepository.findById(productId).orElseThrow(() -> new Exception("Продукта с такими данными нет"));
        Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new Exception("Карзины с такими данными не существует"));
        List<Products> productsList = cart.getProducts();
        productsList.remove(products);
        cartRepo.save(cart);
        return cartMapper.mapToDto(cartRepo.save(cart));
    }

        public Long create (CartDto dto){
            Cart cart = new Cart();
            cart.setId(dto.getId());
            return cartRepo.save(cart).getId();
        }
    }