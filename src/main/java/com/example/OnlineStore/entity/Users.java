package com.example.OnlineStore.entity;

import com.example.OnlineStore.enums.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80)
    private String userName;

    @Column(unique = true)
    private String email;

    @Column(length = 30)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRoles userRoles;

    @OneToOne
    private Cart cart;

    @OneToOne
    private Card card;

    @OneToOne
    private History histories;
}
