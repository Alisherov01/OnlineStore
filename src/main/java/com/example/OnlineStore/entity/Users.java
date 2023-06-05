package com.example.OnlineStore.entity;

import com.example.OnlineStore.enums.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "reset_token_expire_time")
    private LocalDateTime resetTokenExpireTime;

    @OneToOne
    private Cart cart;

    @OneToOne
    private Card card;

    @OneToOne
    private Payment payment;

}
