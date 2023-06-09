package com.example.OnlineStore.repository;

import com.example.OnlineStore.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
    Users findUserByUserName(String username);

    Users findByEmail(String email);

    Users findByResetToken(String resetToken);
}
