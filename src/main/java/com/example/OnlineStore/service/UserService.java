package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.UserDto;
import com.example.OnlineStore.entity.Users;
import com.example.OnlineStore.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class UserService {
    UserRepo userRepo;
    EmailService emailService;

    public Long create(UserDto dto) {
        Users users = new Users();
        dto.setUserName(users.getUserName());
        dto.setPassword(users.getPassword());
        dto.setEmail(users.getEmail());
        return userRepo.save(users).getId();
    }

//    public boolean resetPassword(String email) {
//        Users users = userRepo.findByEmail(email);
//        if(users == null) {
//            return false;
//        }
//
//        String resetToken = UUID.randomUUID().toString();
//        users.setResetToken(resetToken);
//        users.setResetTokenExpireTime(LocalDateTime.now().plusMinutes(60));
//        userRepo.save(users);
//
//        String resetUrl = "http://localhost:8080" + resetToken;
//        String emailText = "Здравствуйте, " + users.getUserName() +
//                "\nДля зброса пароля перейдите по ссылке: " + resetUrl;
//
//        emailService.setMailSender(email, "Сброс пароля ", emailText);
//        return true;
//    }
//
//    public boolean saveNewPassword(String resetToken, String newPassword) {
//        Users users = userRepo.findByResetToken(resetToken);
//        if(users == null || users.getResetTokenExpireTime().isBefore(LocalDateTime.now()))
//            return false;
//
//        users.setPassword(passwordEncoder.encode(newPassword));
//        users.setResetToken(null);
//        users.setResetTokenExpireTime(null);
//        userRepo.save(users);
//        return true;
//    }
}
