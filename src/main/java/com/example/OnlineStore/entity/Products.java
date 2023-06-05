package com.example.OnlineStore.entity;


import com.example.OnlineStore.enums.ProductType;
import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "categories_id", referencedColumnName = "id")
    private Categories categories;
}
