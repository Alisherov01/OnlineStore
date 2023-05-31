package com.example.OnlineStore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80)
    private String fullName;

    @Column(unique = true, length = 16)
    @Min(value = 16)
    private String cartNumber;

    @Column(unique = true, length = 4)
    @Min(value = 4)
    private String CVVCode;

    @Column(length = 100)
    private Integer cardBalance;

    @OneToOne
    private Users users;
}

