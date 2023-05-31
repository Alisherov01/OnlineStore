package com.example.OnlineStore.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToOne
    private Cart cart;

    @OneToOne
    private Card card;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "reset_token_expire_time")
    private LocalDateTime resetTokenExpireTime;

    @OneToMany(mappedBy = "users")
    private List<History> histories;
}
