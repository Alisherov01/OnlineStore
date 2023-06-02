package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.PaymentDto;
import com.example.OnlineStore.dto.UserDto;
import com.example.OnlineStore.entity.ResponseMessage;
import com.example.OnlineStore.enums.ResultCode;
import com.example.OnlineStore.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class UserController {

    private UsersService usersService;

    @PostMapping("/registration")
    public Long userRegistration(@RequestBody UserDto users) {
        return usersService.createUser(users);
    }

    @GetMapping("/reset")
    public String resetPassword(@RequestParam("email") String email) throws Exception {
        return usersService.resetPassword(email);
    }

    @PostMapping("/reset/{resetToken}")
    public String saveNewPassword(@PathVariable("resetToken") String resetToken, @RequestParam String password, Model model) {
        boolean result = usersService.saveNewPassword(resetToken, password);
        model.addAttribute("result", result);
        if (!result)
            return "Неправильный токен!";

        return "Пароль успешно изменен!";
    }

    @PutMapping("update/user/{id}")
    public ResponseMessage<UserDto> update(@PathVariable Long id, @RequestBody UserDto dto) {
        try {
            return new ResponseMessage<>(
                    usersService.update(id, dto),
                    ResultCode.SUCCESS,
                    "Настройки успешно обновлены. ",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("UserService: update ", e);
            return new ResponseMessage<>(
                    null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }
}

