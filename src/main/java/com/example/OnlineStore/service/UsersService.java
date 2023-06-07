package com.example.OnlineStore.service;

import com.example.OnlineStore.dto.UserDto;
import com.example.OnlineStore.entity.Users;
import com.example.OnlineStore.enums.UserRoles;
import com.example.OnlineStore.mappers.UserMapper;
import com.example.OnlineStore.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsersService {
    private final UserRepo userRepo;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public Long registrationUser(UserDto dto){
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
                "\nДля сброса пароля введите токен " + resetToken;

        emailService.sendSimpleMessage(email, "Сброс пароля", emailText);
        return "Проверьте свою почту)";
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

    public List<UserDto> getAllUsers() {
        List<UserDto> dtos = new ArrayList<>();
        List<Users> users = userRepo.findAll();
        for (Users p : users) {
            UserDto dto = new UserDto();
            dto.setUserName(p.getUserName());
            dto.setEmail(p.getEmail());
            dto.setPassword(p.getPassword());
            dto.setUserRoles(p.getUserRoles());
            dtos.add(dto);
        }
        return dtos;
    }

    public UserDto getById(Long id) throws Exception {
        Optional<Users> users = userRepo.findById(id);
        UserDto dto = new UserDto();
        if (users.isPresent()) {
            dto.setUserName(users.get().getUserName());
            dto.setEmail(users.get().getEmail());
            dto.setPassword(users.get().getPassword());
            dto.setUserRoles(users.get().getUserRoles());
        } else {
            throw new Exception("Пользователя с данным Id не существует!");
        }
        return dto;
    }

    public void deleteUsers(Long id) {
        userRepo.deleteById(id);
    }

    public UserDto update(Long id, UserDto dto) throws Exception {
        Users users = userRepo.findById(id).orElseThrow(() ->
                new Exception("Пользователя с такими данными не существует."));
        users.setUserName(dto.getUserName());
        users.setEmail(dto.getEmail());
        return userMapper.mapToDto(userRepo.save(users));
    }
}
