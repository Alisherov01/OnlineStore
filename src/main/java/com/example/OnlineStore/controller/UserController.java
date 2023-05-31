package com.example.OnlineStore.controller;

import com.example.OnlineStore.dto.UserDto;
import com.example.OnlineStore.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private UsersService usersService;

    @PostMapping("/registration")
    public Long userRegistration(@RequestBody UserDto users){
        return usersService.createUser(users);
    }

    @GetMapping("/reset")
    public String resetPassword(@RequestParam("email") String email, Model model) throws Exception {
        String res = usersService.resetPassword(email);
        model.addAttribute("isPresent", usersService.resetPassword(email));

        return res;
    }

    @PostMapping("/reset/{resetToken}")
    public String saveNewPassword(@PathVariable("resetToken") String resetToken, @RequestParam String password, Model model) {
        boolean res = usersService.saveNewPassword(resetToken, password);
        model.addAttribute("result", res);
        if (!res)
            return "forgot-form";

        return "redirect:/auth/main";
    }
}

