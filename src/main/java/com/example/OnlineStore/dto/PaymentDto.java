package com.example.OnlineStore.dto;

import com.example.OnlineStore.entity.Users;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Data
public class PaymentDto {

    private Long id;

    private Users users;

    private LocalDate time = LocalDate.now();

    private Integer orderSum;
}
