package com.example.OnlineStore.entity;


import com.example.OnlineStore.enums.ProductType;
import jakarta.persistence.*;
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

    private String productName;

    private String productColor;

    private String productSize;

    @Column(unique = true, length = 70)
    private String brand;

    @Enumerated(EnumType.STRING)
    ProductType productType;

    private String productPrice;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Categories categories;
}
