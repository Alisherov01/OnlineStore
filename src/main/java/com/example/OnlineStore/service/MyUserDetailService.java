package com.example.OnlineStore.service;

import com.example.OnlineStore.entity.Users;
import com.example.OnlineStore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailService implements UserDetailsService {
        @Autowired
        private UserRepo userRepo;
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Users user = userRepo.findUserByUserName(username);

            return new org.springframework.security.core.userdetails.User
                    (user.getUserName(),user.getPassword(),new ArrayList<>());
        }
    }
