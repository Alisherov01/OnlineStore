package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.UserDto;
import com.example.OnlineStore.entity.Users;
import com.example.OnlineStore.enums.UserRoles;
import com.example.OnlineStore.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsersService {
    private UserRepo userRepo;
    private EmailService emailService;

    private PasswordEncoder passwordEncoder;

    public Long createUser(UserDto dto){
        Users users = new Users();
        users.setUserName((dto.getUserName()));
        users.setEmail((dto.getEmail()));
        users.setPassword((dto.getPassword()));
        users.setUserRoles(UserRoles.USER);
        return userRepo.save(users).getId();
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String resetPassword(String email) throws Exception {
        Users user = userRepo.findByEmail(email);
        if (user == null) {
            throw new Exception("Exception");
        }

        String resetToken = UUID.randomUUID().toString();
        user.setResetToken(resetToken);
        user.setResetTokenExpireTime(LocalDateTime.now().plusMinutes(60));
        userRepo.save(user);

        String emailText = "Здравствуйте, " + user.getUserName() +
                "\nДля сброса пароля перейдите введите токен " + resetToken;

        emailService.sendSimpleMessage(email, "Сброс пароля", emailText);
        return resetToken;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public boolean saveNewPassword(String resetToken, String newPassword) {
        Users user = userRepo.findByResetToken(resetToken);
        if (user == null || user.getResetTokenExpireTime().isBefore(LocalDateTime.now()))
            return false;

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpireTime(null);
        userRepo.save(user);
        return true;
    }

    public boolean isPresentEmail(String email) {
        Users user = userRepo.findByEmail(email);
        return user != null;
    }

}
