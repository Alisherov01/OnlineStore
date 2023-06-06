package com.example.OnlineStore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String fullName;

    @Column(length = 100)
    private String address;

    @DateTimeFormat(pattern = "yyyy:MM:dd")
    private LocalDate orderTime = LocalDate.now();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "orders")
    private List<Products> products;
}

