package com.example.OnlineStore.entity;

import com.example.OnlineStore.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String productName;

    @Column(length = 70)
    private String brand;

    @Enumerated(EnumType.STRING)
    ProductType productType;

    @Column(length = 50)
    private String productPrice;

    @Column(length = 50)
    private String sumDiscount;
}
