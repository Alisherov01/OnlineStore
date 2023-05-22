package com.example.OnlineStore.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private Integer ordersSum;

    @Column(length = 50)
    private Integer discountSum;

    @Column(length = 50)
    private Integer productAmount;

    @OneToMany(mappedBy = "cart")
    private List<Products> products;
}
