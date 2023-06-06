package com.example.OnlineStore.dto;


import com.example.OnlineStore.entity.Products;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDto {

    private String fullName;

    private String address;

    private LocalDate orderTime = LocalDate.now();

    private Integer orderSum;

    private List<Products> products;
}
