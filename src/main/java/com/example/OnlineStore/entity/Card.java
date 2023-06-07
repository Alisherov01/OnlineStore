package com.example.OnlineStore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80)
    private String cardName;

    @Column(unique = true, length = 16)
    @Digits(integer = 16,fraction = 0, message = "Нужно ввести 16-ти значный номер")
    private Integer cartNumber;

    @Column(unique = true, length = 4)
    @Digits(integer = 4,fraction = 4, message = "Нужно ввести 4 числа")
    private Integer CVVCode;

    @Column(length = 100)
    private BigDecimal cardBalance;

    @JsonIgnore
    @OneToOne
    private Users users;
}

