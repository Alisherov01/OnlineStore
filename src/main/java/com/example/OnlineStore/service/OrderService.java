package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.OrderBillDto;
import com.example.OnlineStore.dto.OrderDto;
import com.example.OnlineStore.entity.Orders;
import com.example.OnlineStore.entity.Products;
import com.example.OnlineStore.mappers.OrderMapper;
import com.example.OnlineStore.repository.OrderRepo;
import com.example.OnlineStore.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;
    private final ProductRepo productRepo;

    public OrderBillDto getAll() {
        List<Orders> orders = orderRepo.findAll();
        return getOrderBillDto(orders.stream(), orders);
    }

    public OrderBillDto getById(Long id) throws Exception {
        Optional<Orders> orders = Optional.ofNullable(orderRepo.findById(id).orElseThrow(() ->
                new Exception("Заказа с такими данными не сушкствует.")));
        return getOrderBillDto(orders.stream(), orders);
    }

    private OrderBillDto getOrderBillDto(Stream<Orders> stream, Object orders) {
        List<OrderDto> orderDtoList = stream.map(orderMapper::mapToDto).collect(Collectors.toList());
        BigDecimal sum = BigDecimal.ZERO;
        for (OrderDto orderDto : orderDtoList) {
            for (Products products : orderDto.getProducts()) {
                sum = sum.add(products.getProductPrice());
            }
        }
        OrderBillDto orderBillDto = new OrderBillDto();
        orderBillDto.setOrderDtoList(orderDtoList);
        orderBillDto.setOrderSum(sum);
        return orderBillDto;
    }

    public Long save(OrderDto dto) {
        Orders orders = new Orders();
        orders.setFullName(dto.getFullName());
        orders.setAddress(dto.getAddress());
        orders.setOrderTime(LocalDate.now());
        return orderRepo.save(orders).getId();
    }

    public String deleteOrder(Long id) {
        orderRepo.deleteById(id);
        return "Заказ успешно отменён. ";
    }

    public OrderDto update(Long id, OrderDto dto) throws Exception {
        Orders orders = orderRepo.findById(id).orElseThrow(() ->
                new Exception("Заказа с такими не существует. "));
        orders.setFullName(dto.getFullName());
        orders.setAddress(dto.getAddress());
        orders.setOrderTime(LocalDate.now());
        orderRepo.save(orders);
        return orderMapper.mapToDto(orders);
    }

    public OrderDto addProductToOrder(Long productId, Long orderId) throws Exception {
        Products products = productRepo.findById(productId).orElseThrow(()
                -> new Exception("Продукта с такими данными нет"));
        Orders orders = orderRepo.findById(orderId).orElseThrow(()
                -> new Exception("Заказа с такими данными не существует"));
        List<Products> productsList = orders.getProducts();
        productsList.add(products);
        var saveOrder = orderRepo.save(orders);
        return orderMapper.mapToDto(saveOrder);
    }

    public OrderDto removeProductFromOrder(Long productId, Long orderId) throws Exception {
        Products products = productRepo.findById(productId).orElseThrow(()
                -> new Exception("Продукта с такими данными нет"));
        Orders orders = orderRepo.findById(orderId).orElseThrow(()
                -> new Exception("Заказа с такими данными не существует"));
        List<Products> productsList = orders.getProducts();
        productsList.remove(products);
        var removeOrder = orderRepo.save(orders);
        return orderMapper.mapToDto(removeOrder);

    }
}

