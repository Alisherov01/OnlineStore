package com.example.OnlineStore.entity;


import com.example.OnlineStore.enums.ProductType;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String productName;

    @Column(length = 50)
    private String productColor;

    @Column(length = 50)
    private String productSize;

    @Column(length = 70)
    private String brand;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(length = 50)
    private String productPrice;

    @JsonIgnore
    @ManyToOne
    private Orders orders;

    @JsonIgnore
    @ManyToOne
    private Cart cart;

    @JsonIgnore
    @ManyToOne
    private Categories categories;
}
