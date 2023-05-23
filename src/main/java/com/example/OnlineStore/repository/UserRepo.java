package com.example.OnlineStore.repository;

import com.example.OnlineStore.dto.UserLogin;
import com.example.OnlineStore.dto.UserRegister;
import com.example.OnlineStore.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository <Users,Long> {

   /* @Query(value = "",nativeQuery = true)
    UserLogin userLogin(String email, String password);*/
}
